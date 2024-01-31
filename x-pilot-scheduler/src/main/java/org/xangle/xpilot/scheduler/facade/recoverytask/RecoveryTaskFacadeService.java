package org.xangle.xpilot.scheduler.facade.recoverytask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.xangle.xpilot.scheduler.aspect.annotation.Facade;
import org.xangle.xpilot.scheduler.entity.recovery.RecoveryTaskEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionJpaEntity;
import org.xangle.xpilot.scheduler.entity.transaction.TransactionMongoEntity;
import org.xangle.xpilot.scheduler.model.event.RecoveryTaskCompletedEvent;
import org.xangle.xpilot.scheduler.service.ByteConverterService;
import org.xangle.xpilot.scheduler.service.recoverytask.RecoveryTaskService;
import org.xangle.xpilot.scheduler.service.transaction.TransactionService;

import java.util.List;

@Slf4j
@Facade
@RequiredArgsConstructor
public class RecoveryTaskFacadeService {

    private static final int TASKS_PER_FETCH = 100;
    private static final int RETRY_COUNT = 3;

    private final TransactionService transactionService;
    private final RecoveryTaskService recoveryTaskService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void retryMigration() {
        List<RecoveryTaskEntity> tasks = recoveryTaskService.findAllPendingRetries(TASKS_PER_FETCH, RETRY_COUNT);

        if (tasks.isEmpty()) {
            log.info("No pending recovery tasks");
            return;
        }

        for (RecoveryTaskEntity task : tasks) {
            try {
                List<TransactionJpaEntity> transactions = transactionService.findAllByBlockNumberRange(task.getMinBlockNumber(), task.getMaxBlockNumber());
                List<String> savedIds = transactionService.findAllByBlockNumberRangeMongo(task.getMinBlockNumber(), task.getMaxBlockNumber()).stream()
                        .map(TransactionMongoEntity::getHash)
                        .toList();

                List<TransactionJpaEntity> unSavedTrxs = transactions.stream()
                        .filter(trx -> !savedIds.contains(ByteConverterService.convertToString(trx.getHash())))
                        .toList();

                transactionService.saveAll(unSavedTrxs);

                task.complete();
                recoveryTaskService.save(task);

            } catch (Exception e) {
                log.error("Failed to retry migration", e);

                task.increaseRetryCount();
                recoveryTaskService.save(task);
            }
        }

        applicationEventPublisher.publishEvent(
                RecoveryTaskCompletedEvent.of(
                        tasks.size(),
                        tasks.get(0).getMinBlockNumber(),
                        tasks.get(tasks.size() - 1).getMaxBlockNumber()
                ));
    }
}
