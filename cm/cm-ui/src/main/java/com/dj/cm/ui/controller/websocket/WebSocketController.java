package com.dj.cm.ui.controller.websocket;

import com.dj.cm.event.model.echo.EchoEvent;
import com.dj.cm.event.websocket.config.WebSocketEventConfig;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	@MessageMapping("/echoMessage")
	@SendTo(WebSocketEventConfig.ECHO_TOPIC)
	public EchoEvent echoEvent(EchoEvent echoEvent) throws Exception {
		return echoEvent;
	}
}
