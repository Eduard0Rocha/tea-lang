package com.eduard0rocha.tealang.data.language.term;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TeaAtomTest {
	
	@Test
	void containsVariable_returnsFalse() {
		final TeaAtom atom = new TeaAtom("bob");
		assertFalse(atom.containsVariable());
	}
	
	@Test
	void toPrologString_returnsName() {
		final TeaAtom atom = new TeaAtom("bob");
		assertEquals("bob", atom.toPrologString());
	}
}
