package com.dj.cm.biz.service.date.impl;

import com.dj.cm.biz.service.date.DateService;
import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateServiceConfig {

    @Bean
    public DateService dateService() {
        return new JodaDateService(defaultTimeZone());
    }

    @Bean
    public DateTimeZone defaultTimeZone() {
        return DateTimeZone.UTC;
    }
}
