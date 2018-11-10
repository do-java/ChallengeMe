package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.echo.EchoService;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.Echo;
import com.dj.cm.persistence.repo.echo.EchoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("cached")
@CacheConfig(cacheNames = "com.dj.cm.biz.service.echo")
public class EchoServiceCachedImpl implements EchoService {

	@Autowired
	private EchoRepository echoRepository;

	@Autowired
	private CacheManager cacheManager;	// Can be used for Debug or manual cache control

	@Autowired
	@Qualifier("cached")
	private EchoService self; // Used to call self Cacheable methods

	@Override
	public Iterable<Echo> getAllEchos() {
		return echoRepository.findAll();
	}

	@Override
	@Cacheable
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
	@CachePut(key = "#echo.id")
	public Echo updateEcho(Echo echo) {
		Echo echoToUpdate = self.getEchoById(echo.getId());
		BeanUtils.copyProperties(echo, echoToUpdate, "id");
		Echo savedEcho = echoRepository.save(echoToUpdate);
		return savedEcho;
	}

	@Override
	@CacheEvict
	public void deleteEcho(Long id) {
		Echo echoToDelete = self.getEchoById(id);
		echoRepository.delete(echoToDelete);
	}

	@Override
	public String doEcho(String s, int n) {
		return StringUtils.repeat(s, " ", n);
	}

	@Override
	public String doEcho(Long id, int n) {
		Echo echo = self.getEchoById(id);
		return doEcho(echo.getValue(), n);
	}
}
