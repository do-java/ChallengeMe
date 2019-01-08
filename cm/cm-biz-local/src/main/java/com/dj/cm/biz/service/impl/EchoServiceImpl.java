package com.dj.cm.biz.service.impl;

import com.dj.cm.biz.service.EchoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {

	@Override
	public String doEcho(String s, int n) {
		return StringUtils.repeat(s, " ", n);
	}
}
