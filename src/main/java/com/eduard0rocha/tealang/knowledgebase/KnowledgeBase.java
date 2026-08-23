package com.eduard0rocha.tealang.knowledgebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eduard0rocha.tealang.data.PredicateIndicator;
import com.eduard0rocha.tealang.data.clause.TeaClause;
import com.eduard0rocha.tealang.data.clause.TeaFact;
import com.eduard0rocha.tealang.data.clause.term.TeaAtom;
import com.eduard0rocha.tealang.data.clause.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.clause.term.TeaTerm;

/**
 * Knowledge base.
 * Stores Tea clauses indexed by predicate (functor/arity).
 */
public class KnowledgeBase {
	
	private final Map<PredicateIndicator, List<TeaClause>> clausesByPredicate = new HashMap<>();
	
	/**
	 * Adds a tea clause to the knowledge base.
	 * 
	 * @param clause the tea clause to add
	 */
	public void addClause(final TeaClause clause) {
		final PredicateIndicator key = switch (clause) {
			case TeaFact fact -> keyFor(fact.term());
		};
		clausesByPredicate.computeIfAbsent(key, _ -> new ArrayList<>()).add(clause);
		System.out.println(clausesByPredicate); // TODO: remove this after tests
	}
	
	private PredicateIndicator keyFor(final TeaTerm term) {
		return switch (term) {
			case TeaAtom atom -> new PredicateIndicator(atom.name(), 0);
			case TeaCompoundTerm compoundTerm -> new PredicateIndicator(compoundTerm.functor(), compoundTerm.arguments().size());
		};
	}
}
