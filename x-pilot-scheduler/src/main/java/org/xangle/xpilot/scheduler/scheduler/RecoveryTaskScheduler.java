package org.xangle.xpilot.scheduler.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xangle.xpilot.scheduler.facade.recoverytask.RecoveryTaskFacadeService;

@Component
@RequiredArgsConstructor
public class RecoveryTaskScheduler {

    private final RecoveryTaskFacadeService recoveryTaskFacadeService;

    @Scheduled(cron = "* 0/1 * * * *")
    public void runRecoveryTaskJob() {
        recoveryTaskFacadeService.retryMigration();
    }
}
