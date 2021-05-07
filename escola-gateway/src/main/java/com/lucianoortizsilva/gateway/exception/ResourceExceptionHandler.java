package com.lucianoortizsilva.gateway.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucianoortizsilva.gateway.exception.http.ErroInesperadoException;
import com.lucianoortizsilva.gateway.exception.http.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		final ValidationError validationError = new ValidationError(System.currentTimeMillis(), status.value(), "Erro", "Erro de Argumento Inválido", request.getRequestURI());
		for (final FieldError fe : e.getBindingResult().getFieldErrors()) {
			validationError.addError(fe.getField(), fe.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(validationError);
	}
	
	
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> naoEncontrado(final ObjetoNaoEncontradoException e, HttpServletRequest request) {
		final HttpStatus status = HttpStatus.NOT_FOUND;
		final ValidationError validationError = new ValidationError(System.currentTimeMillis(), status.value(), "Não Encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(validationError);
	}
	
	
	
	@ExceptionHandler(ErroInesperadoException.class)
	public ResponseEntity<StandardError> erroInesperado(final ObjetoNaoEncontradoException e, HttpServletRequest request) {
		final HttpStatus status =  HttpStatus.INTERNAL_SERVER_ERROR;
		final ValidationError validationError = new ValidationError(System.currentTimeMillis(), status.value(), "Ops!", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(validationError);
	}

}