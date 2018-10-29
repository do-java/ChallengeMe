package com.dj.cm.biz.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Entity or Object not found business exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundBizException extends BizException {

	public NotFoundBizException(String message) {
		super(message);
	}
}