package com.dj.cm.biz.remote.client.config;

import com.dj.cm.biz.service.challenge.ChallengeService;
import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.biz.service.filestorage.FileStorageService;
import com.dj.cm.biz.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class BizRemoteClientConfig {

	@Bean
	public HttpInvokerProxyFactoryBean echoServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8090/echoService");
		invoker.setServiceInterface(EchoService.class);
		return invoker;
	}

	@Bean
	public HttpInvokerProxyFactoryBean challengeServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8090/challengeService");
		invoker.setServiceInterface(ChallengeService.class);
		return invoker;
	}

	@Bean
	public HttpInvokerProxyFactoryBean fileStorageServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8090/fileStorageService");
		invoker.setServiceInterface(FileStorageService.class);
		return invoker;
	}

	@Bean
	public HttpInvokerProxyFactoryBean userServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8090/userService");
		invoker.setServiceInterface(UserService.class);
		return invoker;
	}
}
