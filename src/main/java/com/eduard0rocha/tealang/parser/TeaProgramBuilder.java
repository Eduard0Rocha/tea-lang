package com.eduard0rocha.tealang.parser;

import java.util.List;

import com.eduard0rocha.tealang.data.language.TeaProgram;
import com.eduard0rocha.tealang.data.language.clause.TeaClause;
import com.eduard0rocha.tealang.data.language.clause.TeaFact;
import com.eduard0rocha.tealang.data.language.clause.TeaRule;
import com.eduard0rocha.tealang.data.language.query.TeaQuery;
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
	
	/**
	 * Converts a parsed clause into a TeaQuery DTO, without applying fact validation rules
	 * (e.g. queries may contain variables).
	 *
	 * @param ctx the parsed clause context
	 * @return the resulting TeaQuery
	 */
	public TeaQuery toTeaQuery(final TeaParser.ClauseContext ctx) {
		final TeaTerm term = toTeaTerm(ctx.fact().term());
	    return new TeaQuery(term);
	}
	
	private TeaClause toTeaClause(final TeaParser.ClauseContext ctx) {
		if (ctx.fact() != null) {
			return toTeaFact(ctx.fact());
		}
		return toTeaRule(ctx.rule_());
	}
	
	private TeaFact toTeaFact(final TeaParser.FactContext ctx) {
		final TeaTerm term = toTeaTerm(ctx.term());
		return new TeaFact(term);
	}
	
	private TeaRule toTeaRule(final TeaParser.RuleContext ctx) {
		final TeaTerm head = toTeaTerm(ctx.term(0));
		final TeaTerm body = toTeaTerm(ctx.term(1));
		return new TeaRule(head, body);
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
