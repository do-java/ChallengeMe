package com.dj.cm.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggingConfig {
	@Bean
	@Scope("prototype")
	Logger logger(InjectionPoint injectionPoint){
		Class clazz = null;
		if (injectionPoint.getField() != null) {
			clazz = injectionPoint.getField().getDeclaringClass();
		} else if (injectionPoint.getMethodParameter() != null) {
			clazz = injectionPoint.getMethodParameter().getDeclaringClass();
		}

		return LoggerFactory.getLogger(clazz);
	}

}
