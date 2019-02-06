package com.dj.cm.event.websocket.util;

import com.dj.cm.event.model.common.Event;
import com.dj.cm.event.model.echo.EchoEvent;
import com.dj.cm.event.websocket.config.WebSocketEventConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketEventUtil {

	@Autowired
	private SimpMessagingTemplate template;

	public void sendEchoEvent(EchoEvent event) {
		sendEvent(WebSocketEventConfig.ECHO_TOPIC, event);
	}

	private void sendEvent(String topic, Event event) {
		template.convertAndSend(topic, event);
	}
}
