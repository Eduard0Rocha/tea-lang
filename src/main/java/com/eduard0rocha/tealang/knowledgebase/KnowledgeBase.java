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
import com.eduard0rocha.tealang.data.clause.term.TeaVariable;

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
	}
	
	/**
	 * Checks whether the knowledge base contains a clause matching the given term.
	 *
	 * @param term the term to query
	 * @return {@code true} if a matching clause exists, {@code false} otherwise
	 */
	public boolean query(final TeaTerm term) {
		final PredicateIndicator key = keyFor(term);
		final List<TeaClause> predicateClauses = clausesByPredicate.getOrDefault(key, List.of());
		return predicateClauses.stream().anyMatch(clause -> clause.head().equals(term));
		// TODO: error checking (Unknown procedure: c/1 (... could not correct gols) ; Unknown procedure a/0\n\tHowever, there are definitions for:\n\t\ta/2\n false.)
	}
	
	private PredicateIndicator keyFor(final TeaTerm term) {
		return switch (term) {
			case TeaAtom atom -> new PredicateIndicator(atom.name(), 0);
			case TeaCompoundTerm compoundTerm -> new PredicateIndicator(compoundTerm.functor(), compoundTerm.arguments().size());
			// TODO: revisit this when implementing unification
			case TeaVariable variable -> throw new IllegalArgumentException("Cannot compute predicate indicator for a variable: " + variable.toPrologString());
		};
	}
}
