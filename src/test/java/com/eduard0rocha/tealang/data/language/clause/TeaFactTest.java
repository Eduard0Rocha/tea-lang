package com.eduard0rocha.tealang.data.language.clause;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;
import com.eduard0rocha.tealang.exception.InvalidClauseException;

class TeaFactTest {
	
	@Test
    void constructor_withoutVariable_createsFact() {
        final TeaAtom term = new TeaAtom("bob");
        final TeaFact fact = new TeaFact(term);
        assertEquals(term, fact.head());
    }

    @Test
    void constructor_withVariable_throwsInvalidClauseException() {
        final TeaVariable term = new TeaVariable("X");
        assertThrows(InvalidClauseException.class, () -> new TeaFact(term));
    }
}
