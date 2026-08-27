package com.eduard0rocha.tealang.exception;

/**
 * Base exception for Tea-lang related errors.
 */
public abstract class TeaLangException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TeaLangException(final String message) {
        super(message);
    }
}
