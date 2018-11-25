package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.event.config.EventConfig;
import com.dj.cm.event.echo.EchoEvent;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EchoBizEventListener {

	@Autowired
	private Logger logger;

	@RabbitListener(queues = EventConfig.ECHO_BIZ_QUEUE_NAME)
	public void handle(EchoEvent event) {
		logger.info(" [x] Received '" + event + "'");
	}
}
