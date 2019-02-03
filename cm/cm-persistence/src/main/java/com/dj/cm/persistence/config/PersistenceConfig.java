package com.dj.cm.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@PropertySource("persistence.properties")
public class PersistenceConfig {
}
