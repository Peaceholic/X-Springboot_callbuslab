package com.callbuslab.zaritalk.common.config;

import com.callbuslab.zaritalk.model.entity.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //토큰에 User 객체 반환
                return Optional.of(user.getAccountId());
            } else {
                return Optional.empty();
            }
        }
    }
}
