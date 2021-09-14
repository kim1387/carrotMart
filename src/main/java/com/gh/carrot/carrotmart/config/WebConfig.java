package com.gh.carrot.carrotmart.config;

import com.gh.carrot.carrotmart.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer { //스프링 부트가 제공해주는 웹 MVC의 기능들을 확장하고 싶을 때 추가적으로 설정 파일을 만들면 된다.

    // 로그인 체크 인터셉터를 등록
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
    }
}