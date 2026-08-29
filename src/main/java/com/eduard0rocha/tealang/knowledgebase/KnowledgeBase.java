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
import com.eduard0rocha.tealang.exception.UnknownProcedureException;
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
		final List<TeaClause> predicateClauses = clausesByPredicate.computeIfAbsent(key, _ -> new ArrayList<>());
		if (!predicateClauses.contains(clause)) {
			predicateClauses.add(clause);
		}
	}

	/**
	 * Returns an iterator over the substitutions produced by unifying the given term against the knowledge base.
	 *
	 * @param term the term to query
	 * @return an iterator over the successful unifications
	 */
	public Iterator<Substitution> query(final TeaTerm term) {
	    final PredicateIndicator key = keyFor(term);
	    if (!clausesByPredicate.containsKey(key)) {
	        throw new UnknownProcedureException("Unknown procedure: " + key.functor() + "/" + key.arity());
	    }
	    final List<TeaClause> predicateClauses = clausesByPredicate.get(key);
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
