package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.Echo;
import com.dj.cm.persistence.repo.echo.EchoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EchoServiceImpl implements EchoService {

	@Autowired
	private EchoRepository echoRepository;

	@Override
	public Iterable<Echo> getAllEchos() {
		return echoRepository.findAll();
	}

	@Override
	public Echo getEchoById(Long id) {
		Optional<Echo> echoOptional = echoRepository.findById(id);
		if (!echoOptional.isPresent()) {
			throw new NotFoundBizException(String.format("Echo with Id: %s not found", id));
		}

		return echoOptional.get();
	}

	@Override
	public Echo createEcho(Echo echo) {
		return echoRepository.save(echo);
	}

	@Override
	public Echo updateEcho(Echo echo) {
		Echo echoToUpdate = getEchoById(echo.getId());
		BeanUtils.copyProperties(echo, echoToUpdate, "id", "createdDate", "modifiedDate");
		return echoRepository.save(echoToUpdate);
	}

	@Override
	public void deleteEcho(Long id) {
		Echo echoToDelete = getEchoById(id);
		echoRepository.delete(echoToDelete);
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
