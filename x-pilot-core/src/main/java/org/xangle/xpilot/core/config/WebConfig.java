package org.xangle.xpilot.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xangle.xpilot.core.interceptor.GlobalContextInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final GlobalContextInterceptor globalContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalContextInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/auth/signup")
                .excludePathPatterns("/v1/auth/login");
    }
}
