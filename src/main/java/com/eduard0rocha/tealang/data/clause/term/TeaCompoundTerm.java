package com.eduard0rocha.tealang.data.clause.term;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tea compound term DTO.
 *
 * @param functor   the compound term's functor
 * @param arguments the compound term's arguments
 */
public record TeaCompoundTerm(String functor, List<TeaTerm> arguments) implements TeaTerm {

	@Override
	public boolean containsVariable() {
		return arguments.stream().anyMatch(TeaTerm::containsVariable);
	}

	@Override
	public String toPrologString() {
		final String args = arguments.stream()
	            .map(TeaTerm::toPrologString)
	            .collect(Collectors.joining(", "));
	    return functor + "(" + args + ")";
	}
}
