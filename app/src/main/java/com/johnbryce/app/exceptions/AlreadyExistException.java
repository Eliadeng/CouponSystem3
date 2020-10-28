package com.johnbryce.app.exceptions;

public class AlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistException() {
		super("The company is already exists in the system");
	}

	public AlreadyExistException(String message) {
		super(message);
	}

}
