package com.dj.cm.event.amqp.util;

import com.dj.cm.event.amqp.config.AmqpEventConfig;
import com.dj.cm.event.model.common.Event;
import com.dj.cm.event.model.echo.EchoEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Amqp Event Util.
 */
@Component
public class AmqpEventUtil {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private AmqpEventConfig eventConfig;

	public void sendEchoEvent(EchoEvent event) {
		sendEvent(AmqpEventConfig.ECHO_EXCHANGE_NAME, event);
	}

	private void sendEvent(String exchangeName, Event event) {
		if (eventConfig.isEventEnabled()) {
			amqpTemplate.convertAndSend(exchangeName, "", event);
		}
	}

}
