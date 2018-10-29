package com.dj.cm.biz.service.exception;

/**
 * General business exception.
 */
public class BizException extends RuntimeException {

	public BizException() {
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}
}