package com.eduard0rocha.tealang.data.language.clause;

import com.eduard0rocha.tealang.data.language.term.TeaTerm;

/**
 * Tea clause DTO interface.
 */
public sealed interface TeaClause permits TeaFact, TeaRule {
	
	/**
	 * Returns this clause's head term.
	 *
	 * @return the clause's head term
	 */
	TeaTerm head();
}
