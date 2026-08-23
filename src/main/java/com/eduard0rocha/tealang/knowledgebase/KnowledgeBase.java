package com.eduard0rocha.tealang.knowledgebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eduard0rocha.tealang.data.clause.TeaClause;

/**
 * Knowledge base.
 * Stores Tea clauses indexed by predicate (functor/arity).
 */
public class KnowledgeBase {
	
	private final Map<String, List<TeaClause>> clausesByPredicate = new HashMap<>();
}
