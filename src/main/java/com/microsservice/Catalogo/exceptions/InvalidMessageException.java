package com.microsservice.Catalogo.exceptions;

public class InvalidMessageException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMessageException(String name) {
        super(name);
    }

    public InvalidMessageException(String name, Throwable cause) {
        super(name, cause);
    }
}
