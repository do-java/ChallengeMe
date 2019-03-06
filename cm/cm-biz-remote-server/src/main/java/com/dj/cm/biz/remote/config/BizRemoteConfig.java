package com.dj.cm.biz.remote.config;

import com.dj.cm.biz.service.challenge.ChallengeService;
import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.biz.service.filestorage.FileStorageService;
import com.dj.cm.biz.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@PropertySource("biz-remote.properties")
public class BizRemoteConfig {

	@Autowired
	private EchoService echoService;

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private UserService userService;



	@Bean(name = "/challengeService")
	HttpInvokerServiceExporter hallengeService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(challengeService);
		exporter.setServiceInterface(ChallengeService.class);
		return exporter;
	}

	@Bean(name = "/echoService")
	HttpInvokerServiceExporter echoService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(echoService);
		exporter.setServiceInterface(EchoService.class);
		return exporter;
	}

	@Bean(name = "/fileStorageService")
	HttpInvokerServiceExporter fileStorageService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(fileStorageService);
		exporter.setServiceInterface(FileStorageService.class);
		return exporter;
	}

	@Bean(name = "/userService")
	HttpInvokerServiceExporter userService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(userService);
		exporter.setServiceInterface(UserService.class);
		return exporter;
	}

}
