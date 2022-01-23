package com.demo.app.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync()
public class SpringAsyncConfig implements AsyncConfigurer {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }


    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolTaskExecutor();
    }


}