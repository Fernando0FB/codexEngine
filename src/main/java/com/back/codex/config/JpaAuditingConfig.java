package com.back.codex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
    // Esta anotação habilita o JPA Auditing
    // Agora @CreatedDate e @LastModifiedDate funcionarão automaticamente
}