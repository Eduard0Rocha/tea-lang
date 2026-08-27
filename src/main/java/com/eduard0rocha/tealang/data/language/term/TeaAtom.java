package com.eduard0rocha.tealang.data.language.term;

/**
 * Tea atom term DTO.
 * 
 * @param name the atom's name
 */
public record TeaAtom(String name) implements TeaTerm {

	@Override
	public boolean containsVariable() {
		return false;
	}

	@Override
	public String toPrologString() {
		return name;
	}
}
