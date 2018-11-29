package com.dj.cm.event.util;

import com.dj.cm.event.common.Event;
import com.dj.cm.event.config.EventConfig;
import com.dj.cm.event.echo.EchoEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Event Util.
 */
@Component
public class EventUtil {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private EventConfig eventConfig;

	public void sendEchoEvent(EchoEvent event) {
		sendEvent(EventConfig.ECHO_EXCHANGE_NAME, event);
	}

	private void sendEvent(String exchangeName, Event event) {
		if (eventConfig.isEventEnabled()) {
			amqpTemplate.convertAndSend(exchangeName, "", event);
		}
	}

}
