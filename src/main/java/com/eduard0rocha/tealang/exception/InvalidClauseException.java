package com.eduard0rocha.tealang.exception;

/**
 * Thrown when a clause violates a semantic rule of the Tea language.
 */
public class InvalidClauseException extends TeaLangException {

    private static final long serialVersionUID = 1L;

	public InvalidClauseException(final String message) {
        super(message);
    }
}
