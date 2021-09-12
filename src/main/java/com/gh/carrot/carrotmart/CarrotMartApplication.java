package com.gh.carrot.carrotmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class CarrotMartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrotMartApplication.class, args);
    }

}
