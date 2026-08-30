package com.eduard0rocha.tealang.knowledgebase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.language.clause.TeaFact;
import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;
import com.eduard0rocha.tealang.exception.InvalidQueryException;
import com.eduard0rocha.tealang.exception.UnknownProcedureException;
import com.eduard0rocha.tealang.resolution.Substitution;

class KnowledgeBaseTest {
	
	@Test
	void query_afterAddingMatchingClause_returnsSolution() {
		final KnowledgeBase knowledgeBase = new KnowledgeBase();
		final TeaAtom rains = new TeaAtom("rains");
		knowledgeBase.addClause(new TeaFact(rains));
		
		final Iterator<Substitution> result = knowledgeBase.query(rains);
		
		assertTrue(result.hasNext());
	}
	
	@Test
	void query_withNonMatchingTerm_returnsNoSolutions() {
		final KnowledgeBase knowledgeBase = new KnowledgeBase();
		knowledgeBase.addClause(new TeaFact(new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob")))));

		final Iterator<Substitution> result = knowledgeBase.query(new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("alice"))));

		assertFalse(result.hasNext());
	}
	
	@Test
	void addClause_calledTwiceWithSameClause_doesNotDuplicate() {
		final KnowledgeBase knowledgeBase = new KnowledgeBase();
		final TeaAtom rains = new TeaAtom("rains");
		knowledgeBase.addClause(new TeaFact(rains));
		knowledgeBase.addClause(new TeaFact(rains));

		final Iterator<Substitution> result = knowledgeBase.query(rains);
		result.next();

		assertFalse(result.hasNext());
	}

	@Test
	void query_withUndefinedPredicate_throwsUnknownProcedureException() {
		final KnowledgeBase knowledgeBase = new KnowledgeBase();
		assertThrows(UnknownProcedureException.class, () -> knowledgeBase.query(new TeaAtom("rains")));
	}

	@Test
	void query_withVariableAtTopLevel_throwsInvalidQueryException() {
		final KnowledgeBase knowledgeBase = new KnowledgeBase();
		assertThrows(InvalidQueryException.class, () -> knowledgeBase.query(new TeaVariable("X")));
	}
}
