package com.microsservice.Catalogo.exceptions;

public class NoProductsFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoProductsFoundException(String message) {
        super(message);
    }
}
