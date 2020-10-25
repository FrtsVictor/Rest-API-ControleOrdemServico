package com.trainingSpring.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.trainingSpring.exception.EntidadeNaoEncontradaException;
import com.trainingSpring.exception.NegocioException;


@ControllerAdvice
public class ApiExceptionhandler extends ResponseEntityExceptionHandler {

	
	//Pego messagem do messageproperties
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var campos = new ArrayList<Problem.Campo>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
		String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problem.Campo(nome,mensagem));
		}
		
		var problem = Problem.builder()
				.Status(status.value())
				.Titulo("Um ou mais campos invalidos")
				.DataHora(OffsetDateTime.now())
				.Campos(campos)
				.build();
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		var problem = new Problem();
		problem.setTitulo(ex.getMessage());
		problem.setDataHora(OffsetDateTime.now());
		problem.setStatus(status.value());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request){
		var status = HttpStatus.NOT_FOUND;
		
		var problem = new Problem();
		problem.setTitulo(ex.getMessage());
		problem.setDataHora(OffsetDateTime.now());
		problem.setStatus(status.value());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
}
