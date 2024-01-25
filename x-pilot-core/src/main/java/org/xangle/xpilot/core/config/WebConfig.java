package org.xangle.xpilot.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xangle.xpilot.core.interceptor.WorkerIdInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final WorkerIdInterceptor workerIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(workerIdInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/auth/signup")
                .excludePathPatterns("/v1/auth/login");
    }
}
