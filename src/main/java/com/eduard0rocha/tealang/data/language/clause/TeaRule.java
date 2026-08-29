package com.eduard0rocha.tealang.data.language.clause;

import com.eduard0rocha.tealang.data.language.term.TeaTerm;

/**
 * Tea rule clause DTO.
 * 
 * @param head the rule's head
 * @param body the rule's body
 */
public record TeaRule(TeaTerm head, TeaTerm body) implements TeaClause {

	@Override
	public TeaTerm head() {
		return head;
	}
}
