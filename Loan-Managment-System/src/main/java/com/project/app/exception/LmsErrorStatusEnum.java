package com.project.app.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LmsErrorStatusEnum {

	INTERNAL_SERVER_ERROR(10001, "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR),
	CUSTOMER_NOT_FOUND(10002, "customer not found", HttpStatus.NOT_FOUND);

	private int errorCode;

	private String message;

	private HttpStatus httpStatus;

}
