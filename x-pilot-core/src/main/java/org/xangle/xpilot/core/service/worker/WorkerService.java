package org.xangle.xpilot.core.service.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xangle.xpilot.core.entity.WorkerEntity;
import org.xangle.xpilot.core.exception.CustomErrorType;
import org.xangle.xpilot.core.exception.ErrorTypeException;
import org.xangle.xpilot.core.mapper.worker.WorkerEntityMapper;
import org.xangle.xpilot.core.model.request.SignupRequest;
import org.xangle.xpilot.core.repository.worker.MongoWorkerRepository;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final MongoWorkerRepository mongoWorkerRepository;

    @Transactional
    public void signup(final SignupRequest signupRequest) {
        if (mongoWorkerRepository.existsWorkerEntityByEmail(signupRequest.email())) {
            throw new ErrorTypeException("이미 존재하는 이메일입니다.", CustomErrorType.SERVER_ERROR);
        }
        mongoWorkerRepository.save(WorkerEntityMapper.toEntity(signupRequest));
    }

    public WorkerEntity findByEmailAndPassword(String email, String password) {
        return mongoWorkerRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ErrorTypeException("이메일 또는 비밀번호가 일치하지 않습니다.", CustomErrorType.SERVER_ERROR));
    }
}