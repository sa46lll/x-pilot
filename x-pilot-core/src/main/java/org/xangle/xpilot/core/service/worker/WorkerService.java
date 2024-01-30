package org.xangle.xpilot.core.service.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;
    private final MongoWorkerRepository mongoWorkerRepository;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        if (mongoWorkerRepository.existsWorkerEntityByEmail(signupRequest.email())) {
            throw new ErrorTypeException("이미 존재하는 이메일입니다.", CustomErrorType.SERVER_ERROR);
        }

        WorkerEntity worker = WorkerEntityMapper.toEntity(signupRequest);
        worker.encrypt(passwordEncoder.encode(signupRequest.password()));

        mongoWorkerRepository.save(worker);
    }

    public WorkerEntity findByEmailAndPassword(String email, String password) {
        return mongoWorkerRepository.findByEmail(email)
                .filter(worker -> isPasswordCorrect(password, worker.getPassword()))
                .orElseThrow(() -> new ErrorTypeException("이메일 또는 비밀번호가 일치하지 않습니다.", CustomErrorType.SERVER_ERROR));
    }

    private boolean isPasswordCorrect(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
