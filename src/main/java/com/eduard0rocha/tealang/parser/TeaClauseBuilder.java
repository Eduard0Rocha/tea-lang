package com.eduard0rocha.tealang.parser;

import java.util.List;

import com.eduard0rocha.tealang.data.TeaProgram;
import com.eduard0rocha.tealang.data.clause.TeaClause;
import com.eduard0rocha.tealang.data.clause.TeaFact;
import com.eduard0rocha.tealang.data.clause.term.TeaAtom;
import com.eduard0rocha.tealang.data.clause.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.clause.term.TeaTerm;

/**
 * Converts ANTLR parse trees into TeaClause DTOs.
 */
public class TeaClauseBuilder extends TeaBaseVisitor<TeaProgram> {

	@Override
	public TeaProgram visitProgram(final TeaParser.ProgramContext ctx) {
	    final List<TeaClause> clauses = ctx.clause().stream()
	            .map(this::toTeaClause)
	            .toList();
	    return new TeaProgram(clauses);
	}
	
	private TeaClause toTeaClause(final TeaParser.ClauseContext ctx) {
		return toTeaFact(ctx.fact());
	}
	
	private TeaFact toTeaFact(final TeaParser.FactContext ctx) {
		final TeaTerm term = toTeaTerm(ctx.term());
		return new TeaFact(term);
	}
	
	private TeaTerm toTeaTerm(final TeaParser.TermContext ctx) {
		if (ctx.termArgs() == null) {
			return new TeaAtom(ctx.ATOM().getText());
		}
		final TeaParser.TermArgsContext termArgsCtx = ctx.termArgs();
		final List<TeaTerm> arguments = termArgsCtx.term()
				.stream()
				.map(this::toTeaTerm)
				.toList();
		return new TeaCompoundTerm(ctx.ATOM().getText(), arguments);
	}
}
