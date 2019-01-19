package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.echo.EchoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EchoServiceImpl implements EchoService {

    @Autowired
    private EchoRepository echoRepository;

    @Override
    public String doEcho(String s, int n) {
        return StringUtils.repeat(s, " ", n);
    }

    @Override
    public String getEcho(Long id, int n) {
        Optional<Echo> optionalEcho = echoRepository.findById(id);
        if (optionalEcho.isPresent()) {
            return doEcho(optionalEcho.get().getValue(), n);
        } else {
            return "Not found";
        }
    }
}
