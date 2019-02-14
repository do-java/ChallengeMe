package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.event.amqp.util.AmqpEventUtil;
import com.dj.cm.event.model.echo.EchoCreatedEvent;
import com.dj.cm.event.model.echo.EchoDeletedEvent;
import com.dj.cm.event.model.echo.EchoUpdatedEvent;
import com.dj.cm.event.websocket.util.WebSocketEventUtil;
import com.dj.cm.model.entity.Echo;
import com.dj.cm.persistence.repo.echo.EchoRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class EchoServiceImpl implements EchoService {

	@Autowired
	private Logger logger;

	@Autowired
	private EchoRepository echoRepository;

	@Autowired
	private AmqpEventUtil amqpEventUtil;

	@Autowired
	private WebSocketEventUtil webSocketEventUtil;

	@Override
	public List<Echo> getAllEchos() {
		return echoRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Echo getEchoById(Long id) {
		Optional<Echo> echoOptional = echoRepository.findById(id);
		if (!echoOptional.isPresent()) {
			final String msg = String.format("Echo with Id: %s not found", id);
			logger.error(msg);
			throw new NotFoundBizException(msg);
		}

		return echoOptional.get();
	}

	@Override
	public Echo createEcho(Echo echo) {
		Echo result = echoRepository.save(echo);
		amqpEventUtil.sendEchoEvent(new EchoCreatedEvent(result));
		webSocketEventUtil.sendEchoEvent(new EchoCreatedEvent(result));
		return result;
	}

	@Override
	public Echo updateEcho(Echo echo) {
		Echo echoToUpdate = getEchoById(echo.getId());
		Echo echoToUpdateCopy = echoToUpdate.toBuilder().build(); // Copy/clone, using lombok
		BeanUtils.copyProperties(echo, echoToUpdate, "id");
		Echo result = echoRepository.save(echoToUpdate);

		if (!echoToUpdateCopy.equals(result)) {
			// Send event if entity updated
			amqpEventUtil.sendEchoEvent(new EchoUpdatedEvent(echoToUpdateCopy, result));
			webSocketEventUtil.sendEchoEvent(new EchoUpdatedEvent(echoToUpdateCopy, result));
		}

		return result;
	}

	@Override
	public void deleteEcho(Long id) {
		Echo echoToDelete = getEchoById(id);
		echoRepository.delete(echoToDelete);
		amqpEventUtil.sendEchoEvent(new EchoDeletedEvent(echoToDelete));
		webSocketEventUtil.sendEchoEvent(new EchoDeletedEvent(echoToDelete));
	}

	@Override
	public String doEcho(String s, int n) {
		return StringUtils.repeat(s, " ", n);
	}

	@Override
	public String doEcho(Long id, int n) {
		Echo echo = getEchoById(id);
		return doEcho(echo.getValue(), n);
	}
}
