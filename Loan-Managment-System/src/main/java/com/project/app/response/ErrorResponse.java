package com.project.app.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ErrorResponse {

	@JsonIgnore
	private int status;

	@JsonIgnore
	private String url;

	@JsonIgnore
	private int errorCode;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> messages;

	private String timestamp;

	public ErrorResponse(int status, String url, int errorCode, String message) {
		this.status = status;
		this.url = url;
		this.errorCode = errorCode;
		this.message = message;
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}

	public ErrorResponse(int status, String url, int errorCode, Map<String, String> messages) {
		this(status, url, errorCode, "");
		this.messages = messages;
	}

}
