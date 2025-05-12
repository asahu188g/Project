package com.project.app.exception;

import lombok.Getter;

@Getter
public class LmsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final transient LmsErrorStatusEnum errorStatus;

	public LmsException(LmsErrorStatusEnum errorStatus) {
		super(errorStatus.getMessage());
		this.errorStatus = errorStatus;
	}

	public LmsException(LmsErrorStatusEnum errorCode, String message) {
		super(message);
		this.errorStatus = errorCode;
	}
}
