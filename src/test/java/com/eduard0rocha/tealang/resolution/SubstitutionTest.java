package com.eduard0rocha.tealang.resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.language.term.TeaAtom;

class SubstitutionTest {

	@Test
	void isBound_withoutBinding_returnsFalse() {
		final Substitution substitution = new Substitution();
		assertFalse(substitution.isBound("X"));
	}

	@Test
	void isBound_afterBindingVariable_returnsTrueOnlyForThatVariable() {
		final Substitution substitution = new Substitution();
		substitution.bind("X", new TeaAtom("bob"));

		assertTrue(substitution.isBound("X"));
		assertFalse(substitution.isBound("Y"));
	}

	@Test
	void get_afterBindingVariable_returnsBoundTerm() {
		final Substitution substitution = new Substitution();
		substitution.bind("X", new TeaAtom("bob"));

		assertEquals(new TeaAtom("bob"), substitution.get("X"));
	}

	@Test
	void isEmpty_withoutBindings_returnsTrue() {
		final Substitution substitution = new Substitution();
		assertTrue(substitution.isEmpty());
	}

	@Test
	void isEmpty_withBindings_returnsFalse() {
		final Substitution substitution = new Substitution();
		substitution.bind("X", new TeaAtom("bob"));

		assertFalse(substitution.isEmpty());
	}

	@Test
	void toPrologString_withoutBindings_returnsTrue() {
		final Substitution substitution = new Substitution();
		assertEquals("true", substitution.toPrologString());
	}

	@Test
	void toPrologString_withSingleBinding_returnsBindingLine() {
		final Substitution substitution = new Substitution();
		substitution.bind("X", new TeaAtom("bob"));

		assertEquals("X = bob", substitution.toPrologString());
	}

	@Test
	void toPrologString_withMultipleBindings_returnsCommaSeparatedLines() {
		final Substitution substitution = new Substitution();
		substitution.bind("X", new TeaAtom("bob"));
		substitution.bind("Y", new TeaAtom("alice"));

		final String result = substitution.toPrologString();

		assertTrue(result.contains("X = bob"));
		assertTrue(result.contains("Y = alice"));
		assertTrue(result.contains(",\n"));
	}

	@Test
	void toPrologString_withOnlyAnonymousVariable_returnsTrue() {
		final Substitution substitution = new Substitution();
		substitution.bind("_", new TeaAtom("bob"));

		assertEquals("true", substitution.toPrologString());
	}

	@Test
	void toPrologString_withAnonymousAndNamedVariable_hidesAnonymous() {
		final Substitution substitution = new Substitution();
		substitution.bind("_", new TeaAtom("bob"));
		substitution.bind("X", new TeaAtom("alice"));

		assertEquals("X = alice", substitution.toPrologString());
	}
}
