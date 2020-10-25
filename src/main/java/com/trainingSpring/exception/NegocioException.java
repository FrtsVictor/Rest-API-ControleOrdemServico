package com.trainingSpring.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -6700703966171843107L;

	public NegocioException(String message) {
		super(message);
	}
	
}
