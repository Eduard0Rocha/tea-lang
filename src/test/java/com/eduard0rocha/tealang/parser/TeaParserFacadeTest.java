package com.eduard0rocha.tealang.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.language.TeaProgram;
import com.eduard0rocha.tealang.data.language.clause.TeaFact;
import com.eduard0rocha.tealang.data.language.query.TeaQuery;
import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;

class TeaParserFacadeTest {

	@Test
	void parseProgram_withSimpleFact_returnsProgramWithFact() {
		final TeaProgram program = TeaParserFacade.parseProgram("rains.");
		assertEquals(List.of(new TeaFact(new TeaAtom("rains"))), program.clauses());
	}

	@Test
	void parseProgram_withCompoundFact_returnsProgramWithCompoundTerm() {
		final TeaProgram program = TeaParserFacade.parseProgram("father(tom, bob).");
		final TeaCompoundTerm expected = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob")));
		assertEquals(List.of(new TeaFact(expected)), program.clauses());
	}

	@Test
	void parseProgram_withNestedCompoundFact_returnsNestedTerm() {
		final TeaProgram program = TeaParserFacade.parseProgram("location(book, shelf(office)).");
		final TeaCompoundTerm nested = new TeaCompoundTerm("shelf", List.of(new TeaAtom("office")));
		final TeaCompoundTerm expected = new TeaCompoundTerm("location", List.of(new TeaAtom("book"), nested));
		assertEquals(List.of(new TeaFact(expected)), program.clauses());
	}

	@Test
	void parseProgram_withMultipleFacts_returnsAllClauses() {
		final TeaProgram program = TeaParserFacade.parseProgram("rains. wind.");
		assertEquals(List.of(new TeaFact(new TeaAtom("rains")), new TeaFact(new TeaAtom("wind"))), program.clauses());
	}

	@Test
	void parseProgram_withInvalidSyntax_throwsParseCancellationException() {
		assertThrows(ParseCancellationException.class, () -> TeaParserFacade.parseProgram("rains"));
	}

	@Test
	void parseQuery_withVariable_returnsQueryWithVariable() {
		final TeaQuery query = TeaParserFacade.parseQuery("father(tom, X).");
		final TeaCompoundTerm expected = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaVariable("X")));
		assertEquals(expected, query.term());
	}

	@Test
	void parseQuery_withTrailingContent_throwsParseCancellationException() {
		assertThrows(ParseCancellationException.class, () -> TeaParserFacade.parseQuery("a.b"));
	}
}