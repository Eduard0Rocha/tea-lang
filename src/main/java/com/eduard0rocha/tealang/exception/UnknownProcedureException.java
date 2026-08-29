package com.eduard0rocha.tealang.exception;

/**
 * Thrown when a query references a predicate that has no definitions in the knowledge base.
 */
public class UnknownProcedureException extends TeaLangException {

    private static final long serialVersionUID = 1L;

	public UnknownProcedureException(final String message) {
        super(message);
    }
}
