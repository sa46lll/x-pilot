package org.xangle.xpilot.scheduler.service.recoverytask;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xangle.xpilot.scheduler.entity.recovery.RecoveryTaskEntity;
import org.xangle.xpilot.scheduler.repository.recoverytask.RecoveryTaskMongoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecoveryTaskService {

    private final RecoveryTaskMongoRepository recoveryTaskMongoRepository;

    public void save(RecoveryTaskEntity recoveryTaskEntity) {
        recoveryTaskMongoRepository.save(recoveryTaskEntity);
    }

    public List<RecoveryTaskEntity> findAllPendingRetries(int count, int retryCount) {
        Pageable pageable = PageRequest.of(0, count);
        return recoveryTaskMongoRepository.findAllByIsCompletedAndRetryCountLessThan(false, retryCount, pageable);
    }
}
