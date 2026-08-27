package com.eduard0rocha.tealang.data.clause.term;

/**
 * Tea term DTO interface.
 */
public sealed interface TeaTerm permits TeaAtom, TeaCompoundTerm, TeaVariable {
	
	/**
	 * Checks whether this term contains a variable, directly or nested within its arguments.
	 *
	 * @return {@code true} if this term contains a variable, {@code false} otherwise
	 */
	boolean containsVariable();
	
	/**
	 * Returns this term formatted as valid Tea syntax.
	 *
	 * @return the term formatted as Tea syntax
	 */
	String toPrologString();
}
