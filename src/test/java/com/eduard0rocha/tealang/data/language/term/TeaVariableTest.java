package com.eduard0rocha.tealang.data.language.term;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TeaVariableTest {
	
	@Test
	void containsVariable_returnsTrue() {
		final TeaVariable variable = new TeaVariable("X");
		assertTrue(variable.containsVariable());
	}
	
	@Test
	void toPrologString_returnsName() {
		final TeaVariable variable = new TeaVariable("X");
		assertEquals("X", variable.toPrologString());
	}
}
