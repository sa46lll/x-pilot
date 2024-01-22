package org.xangle.xpilot.core.controller.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xangle.xpilot.core.model.request.SignupRequest;
import org.xangle.xpilot.core.service.WorkerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/worker")
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping("/signup")
    public void signup(@RequestBody final SignupRequest signupRequest) {
        workerService.signup(signupRequest);
    }
}
