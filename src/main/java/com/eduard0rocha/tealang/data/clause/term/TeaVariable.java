package com.eduard0rocha.tealang.data.clause.term;

/**
 * Tea variable term DTO.
 * 
 * @param name the variable's name
 */
public record TeaVariable(String name) implements TeaTerm {

	@Override
	public boolean containsVariable() {
		return true;
	}

	@Override
	public String toPrologString() {
		return name;
	}
}
