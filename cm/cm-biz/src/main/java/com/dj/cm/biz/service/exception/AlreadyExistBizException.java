package com.dj.cm.biz.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistBizException extends BizException {

    public AlreadyExistBizException(String message){
        super(message);
    }
}
