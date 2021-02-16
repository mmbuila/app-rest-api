package org.chembotula.app.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdviceHandler {
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorService> exceptionHander(ServiceException exception, HttpServletRequest request) {
		ErrorService errorService = new ErrorService();
		errorService.setCodeStatus(exception.getCodeStatus());
		errorService.setError(exception.getMessage());
		errorService.setPath(request.getRequestURI());
		return new ResponseEntity<ErrorService>(errorService, HttpStatus.valueOf(errorService.getCodeStatus()));
	}
}
