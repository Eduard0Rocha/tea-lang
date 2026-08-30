package com.eduard0rocha.tealang.data.language.term;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class TeaCompoundTermTest {
	
	@Test
	void containsVariable_withVariable_returnsTrue() {
		final TeaVariable variable = new TeaVariable("X");
		final TeaCompoundTerm compoundTerm = new TeaCompoundTerm("color", List.of(variable));
		assertTrue(compoundTerm.containsVariable());
	}
	
	@Test
	void containsVariable_withoutVariable_returnsFalse() {
		final TeaAtom atom = new TeaAtom("green");
		final TeaCompoundTerm compoundTerm = new TeaCompoundTerm("color", List.of(atom));
		assertFalse(compoundTerm.containsVariable());
	}
	
	@Test
	void toPrologString_withSingleArgument_returnsFunctorWithArgument() {
		final TeaAtom atom = new TeaAtom("green");
		final TeaCompoundTerm compoundTerm = new TeaCompoundTerm("color", List.of(atom));
		assertEquals("color(green)", compoundTerm.toPrologString());
	}
	
	@Test
	void toPrologString_withMultipleArguments_returnsCommaSeparated() {
		final TeaAtom atomA = new TeaAtom("alice");
		final TeaAtom atomB = new TeaAtom("bob");
		final TeaCompoundTerm compoundTerm = new TeaCompoundTerm("likes", List.of(atomA, atomB));
		assertEquals("likes(alice, bob)", compoundTerm.toPrologString());
	}
}