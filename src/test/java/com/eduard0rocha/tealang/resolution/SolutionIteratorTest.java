package com.eduard0rocha.tealang.resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.language.clause.TeaClause;
import com.eduard0rocha.tealang.data.language.clause.TeaFact;
import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;

class SolutionIteratorTest {

	@Test
	void hasNext_withNoCandidates_returnsFalse() {
		final Iterator<Substitution> iterator = new SolutionIterator(new TeaAtom("rains"), List.of());
		assertFalse(iterator.hasNext());
	}

	@Test
	void hasNext_withMatchingCandidate_returnsTrue() {
		final TeaClause candidate = new TeaFact(new TeaAtom("rains"));
		
		final Iterator<Substitution> iterator = new SolutionIterator(new TeaAtom("rains"), List.of(candidate));
		
		assertTrue(iterator.hasNext());
	}

	@Test
	void hasNext_withNonMatchingCandidate_returnsFalse() {
		final TeaClause candidate = new TeaFact(new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob"))));
		final TeaTerm query = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("alice")));

		final Iterator<Substitution> iterator = new SolutionIterator(query, List.of(candidate));

		assertFalse(iterator.hasNext());
	}

	@Test
	void next_withMatchingCandidate_returnsCorrectBinding() {
		final TeaClause candidate = new TeaFact(new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob"))));
		final TeaTerm query = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaVariable("X")));

		final Iterator<Substitution> iterator = new SolutionIterator(query, List.of(candidate));
		final Substitution solution = iterator.next();

		assertEquals(new TeaAtom("bob"), solution.get("X"));
	}

	@Test
	void next_withMultipleMatchingCandidates_returnsEachSolutionOnce() {
		final TeaClause first = new TeaFact(new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob"))));
		final TeaClause second = new TeaFact(new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("alice"))));
		final TeaTerm query = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaVariable("X")));

		final Iterator<Substitution> iterator = new SolutionIterator(query, List.of(first, second));

		assertEquals(new TeaAtom("bob"), iterator.next().get("X"));
		assertEquals(new TeaAtom("alice"), iterator.next().get("X"));
		assertFalse(iterator.hasNext());
	}

	@Test
	void next_withoutMoreSolutions_throwsNoSuchElementException() {
		final Iterator<Substitution> iterator = new SolutionIterator(new TeaAtom("rains"), List.of());
		assertThrows(NoSuchElementException.class, iterator::next);
	}
}
