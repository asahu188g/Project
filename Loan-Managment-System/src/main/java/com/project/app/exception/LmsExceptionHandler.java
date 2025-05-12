package com.project.app.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.app.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for HMS application using
 * Spring's @ControllerAdvice. This class intercepts various exceptions and
 * returns appropriate error responses.
 */
@Slf4j
@ControllerAdvice
public class LmsExceptionHandler {

	/**
	 * The HttpServletRequest used to extract request URI for error responses.
	 */
	@Autowired
	private HttpServletRequest req;

	/**
	 * Handles exceptions of type {@link HmsException}.
	 *
	 * @param exception the authentication exception that occurred
	 * @return a ResponseEntity containing the error response and appropriate HTTP
	 *         status
	 */
	@ExceptionHandler(LmsException.class)
	public ResponseEntity<ErrorResponse> handleSlmsException(LmsException exception) {
		
		 String message = Optional.ofNullable(exception.getMessage())
                 .orElse(exception.getErrorStatus().getMessage());
		 ResponseEntity<ErrorResponse> errorResponse =  ResponseEntity.status(exception.getErrorStatus().getHttpStatus())
             .body(new ErrorResponse(
                 exception.getErrorStatus().getHttpStatus().value(),
                 req.getRequestURI(),
                 exception.getErrorStatus().getErrorCode(),
                 message
             ));
		 log.error("hms exception occurred. Error: [{}]", errorResponse);
		 return errorResponse;
	} 

	/**
	 * Handles exceptions of type {@link MethodArgumentNotValidException}. This
	 * exception typically occurs when a method argument annotated with @Valid fails
	 * validation.
	 *
	 * @param exception the method argument validation exception that occurred
	 * @return a ResponseEntity containing the error response and appropriate HTTP
	 *         status
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
		 Map<String, String> errors = new HashMap<>();
		 exception.getBindingResult().getFieldErrors().forEach(error -> 
	            errors.put(error.getField(), error.getDefaultMessage())
	        );
			
			ResponseEntity<ErrorResponse> errorResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body(new ErrorResponse(
	                                 HttpStatus.BAD_REQUEST.value(), 
	                                 req.getRequestURI(), 
	                                 10000,
	                                 errors
	                             ));
			log.error("Validation exception occurred. Error: [{}]", errorResponse);
			return errorResponse;
			
	}

	/**
	 * Handles general exceptions of type {@link Exception}.
	 *
	 * @param exception the exception that occurred
	 * @return a ResponseEntity containing the error response and appropriate HTTP
	 *         status
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		if (exception instanceof HttpMessageNotReadableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new ErrorResponse(
	                        HttpStatus.BAD_REQUEST.value(), 
	                        req.getRequestURI(), 
	                        0, 
	                        "Required request body is missing"
	                    ));
			}
		
		ResponseEntity<ErrorResponse> errorResponse =  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ErrorResponse(
	                                 HttpStatus.INTERNAL_SERVER_ERROR.value(), 
	                                 req.getRequestURI(), 
	                                 0, 
	                                 exception.getMessage()
	                             ));
	        log.error("Unexpected error occurred. Error: [{}]", errorResponse);
	        return errorResponse;
	}
}

