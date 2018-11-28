package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.event.config.EventConfig;
import com.dj.cm.event.echo.EchoEvent;
import org.slf4j.Logger;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EchoAnonymousQueuEventListener {

	@Autowired
	private Logger logger;

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue,
			exchange = @Exchange(value = EventConfig.ECHO_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
	))
	public void handle(EchoEvent event) {
		logger.info(" [x] Received '" + event + "'");
	}
}
