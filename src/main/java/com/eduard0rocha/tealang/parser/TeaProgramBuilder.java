package com.eduard0rocha.tealang.parser;

import java.util.List;

import com.eduard0rocha.tealang.data.TeaProgram;
import com.eduard0rocha.tealang.data.language.clause.TeaClause;
import com.eduard0rocha.tealang.data.language.clause.TeaFact;
import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;

/**
 * Converts ANTLR parse trees into TeaProgram DTOs.
 */
public class TeaProgramBuilder extends TeaBaseVisitor<TeaProgram> {

	@Override
	public TeaProgram visitProgram(final TeaParser.ProgramContext ctx) {
	    final List<TeaClause> clauses = ctx.clause().stream()
	            .map(this::toTeaClause)
	            .toList();
	    return new TeaProgram(clauses);
	}
	
	// This method is public so it can be used to parse queries (clauses) from stdin.
	public TeaClause toTeaClause(final TeaParser.ClauseContext ctx) {
		return toTeaFact(ctx.fact());
	}
	
	private TeaFact toTeaFact(final TeaParser.FactContext ctx) {
		final TeaTerm term = toTeaTerm(ctx.term());
		return new TeaFact(term);
	}
	
	private TeaTerm toTeaTerm(final TeaParser.TermContext ctx) {
		if (ctx.VARIABLE() != null) { // VARIABLE
			return new TeaVariable(ctx.VARIABLE().getText());
		}
		if (ctx.termArgs() != null) { // ATOM LPAREN termArgs RPAREN
			final TeaParser.TermArgsContext termArgsCtx = ctx.termArgs();
			final List<TeaTerm> arguments = termArgsCtx.term()
					.stream()
					.map(this::toTeaTerm)
					.toList();
			return new TeaCompoundTerm(ctx.ATOM().getText(), arguments);
		}
		return new TeaAtom(ctx.ATOM().getText()); // ATOM
	}
}
