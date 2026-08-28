package com.eduard0rocha.tealang.exception;

/**
 * Thrown when a query cannot be evaluated against the knowledge base.
 */
public class InvalidQueryException extends TeaLangException {

    private static final long serialVersionUID = 1L;

	public InvalidQueryException(final String message) {
        super(message);
    }
}