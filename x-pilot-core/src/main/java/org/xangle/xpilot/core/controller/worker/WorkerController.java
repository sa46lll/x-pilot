package org.xangle.xpilot.core.controller.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.facade.WorkerFacadeService;
import org.xangle.xpilot.core.jwt.XPilotWorker;
import org.xangle.xpilot.core.model.request.LoginRequest;
import org.xangle.xpilot.core.model.request.SignupRequest;
import org.xangle.xpilot.core.model.response.TokenResponse;
import org.xangle.xpilot.core.service.WorkerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/worker")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerFacadeService workerFacadeService;

    @PostMapping("/signup")
    public void signup(@RequestBody final SignupRequest signupRequest) {
        workerService.signup(signupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody final LoginRequest loginRequest) {
        return workerFacadeService.login(loginRequest);
    }
}
