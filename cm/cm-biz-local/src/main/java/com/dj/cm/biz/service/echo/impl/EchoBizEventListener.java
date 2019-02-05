package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.event.amqp.config.AmqpEventConfig;
import com.dj.cm.event.model.echo.EchoEvent;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "event.enabled") // From application.properties
public class EchoBizEventListener {

	@Autowired
	private Logger logger;

	@RabbitListener(queues = AmqpEventConfig.ECHO_BIZ_QUEUE_NAME)
	public void handle(EchoEvent event) {
		logger.info(" [x] Received '" + event + "'");
	}
}
