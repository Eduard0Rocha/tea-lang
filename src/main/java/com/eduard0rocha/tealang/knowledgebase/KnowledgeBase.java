package com.eduard0rocha.tealang.knowledgebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.eduard0rocha.tealang.data.PredicateIndicator;
import com.eduard0rocha.tealang.data.language.clause.TeaClause;
import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;
import com.eduard0rocha.tealang.exception.InvalidQueryException;
import com.eduard0rocha.tealang.resolution.SolutionIterator;
import com.eduard0rocha.tealang.resolution.Substitution;

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
		final PredicateIndicator key = keyFor(clause.head());
		clausesByPredicate.computeIfAbsent(key, _ -> new ArrayList<>()).add(clause);
	}

	// TODO: error checking (Unknown procedure: c/1 (... could not correct gols) ; Unknown procedure a/0\n\tHowever, there are definitions for:\n\t\ta/2\n false.)
	/**
	 * Returns an iterator over the substitutions produced by unifying the given term against the knowledge base.
	 *
	 * @param term the term to query
	 * @return an iterator over the successful unifications
	 */
	public Iterator<Substitution> query(final TeaTerm term) {
	    final PredicateIndicator key = keyFor(term);
	    final List<TeaClause> predicateClauses = clausesByPredicate.getOrDefault(key, List.of());
	    return new SolutionIterator(term, predicateClauses);
	}
	
	private PredicateIndicator keyFor(final TeaTerm term) {
		return switch (term) {
			case TeaAtom atom -> new PredicateIndicator(atom.name(), 0);
			case TeaCompoundTerm compoundTerm -> new PredicateIndicator(compoundTerm.functor(), compoundTerm.arguments().size());
			case TeaVariable variable -> throw new InvalidQueryException("Cannot compute predicate indicator for a variable: " + variable.toPrologString());
		};
	}
}
