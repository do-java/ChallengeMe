package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.Echo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("inmemory")
public class EchoServiceInmemoryImpl implements EchoService {

	/**
	 * Echos data.
	 */
	private Map<Long, Echo> data = new HashMap<>();

	private long idCounter = 0;

	@Override
	public List<Echo> getAllEchos() {
		return new ArrayList<>(data.values());
	}

	@Override
	public Echo getEchoById(Long id) {
		Echo echo = data.get(id);
		if (echo == null) {
			throw new NotFoundBizException(String.format("Echo with Id: %s not found", id));
		}

		return echo;
	}

	@Override
	public Echo createEcho(Echo echo) {
		Echo echoToCreate = new Echo();
		BeanUtils.copyProperties(echo, echoToCreate);
		echoToCreate.setId(idCounter++);

		data.put(echoToCreate.getId(), echoToCreate);

		return echoToCreate;
	}

	@Override
	public Echo updateEcho(Echo echo) {
		Echo echoToUpdate = getEchoById(echo.getId());
		BeanUtils.copyProperties(echo, echoToUpdate, "id");
		return echoToUpdate;
	}

	@Override
	public void deleteEcho(Long id) {
		Echo echoToDelete = getEchoById(id);
		data.remove(id);
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
