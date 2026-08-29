package com.eduard0rocha.tealang.data.language.clause;

import com.eduard0rocha.tealang.data.language.term.TeaTerm;
import com.eduard0rocha.tealang.exception.InvalidClauseException;

/**
 * Tea fact clause DTO.
 * 
 * @param term the fact's term
 */
public record TeaFact(TeaTerm term) implements TeaClause {
	
	// Enforces that facts never contain variables
	public TeaFact {
        if (term.containsVariable()) { 
            throw new InvalidClauseException("Facts cannot contain variables: " + term.toPrologString());
        }
    }

	@Override
	public TeaTerm head() {
		return term;
	}
}
