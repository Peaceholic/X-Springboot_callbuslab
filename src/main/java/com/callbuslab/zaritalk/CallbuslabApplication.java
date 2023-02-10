package com.callbuslab.zaritalk;

import com.callbuslab.zaritalk.common.resolver.BrowserInfoArgumentResolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ServletComponentScan
@SpringBootApplication
public class CallbuslabApplication extends WebMvcConfigurationSupport {

    private final BrowserInfoArgumentResolver browserInfoArgumentResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(browserInfoArgumentResolver);
    }

    public static void main(String[] args) {
        SpringApplication.run(CallbuslabApplication.class, args);
    }
}
