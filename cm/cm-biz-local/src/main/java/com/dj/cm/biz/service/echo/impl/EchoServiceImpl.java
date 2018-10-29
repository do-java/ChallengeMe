package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.model.entity.Echo;
import com.dj.cm.persistence.repo.echo.EchoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {

	@Autowired
	private EchoRepository echoRepository;

	@Override
	public String doEcho(String s, int n) {
		return StringUtils.repeat(s, " ", n);
	}

	@Override
	public String doEcho(Long id, int n) {
		Echo echo = getEchoById(id);
		if (echo != null) {
			return doEcho(echo.getValue(), n);
		} else {
			return "Not found";
		}
	}

	@Override
	public Iterable<Echo> getAllEchos() {
		return echoRepository.findAll();
	}

	@Override
	public Echo getEchoById(Long id) {
		return echoRepository.findById(id).orElse(null);
	}
}
