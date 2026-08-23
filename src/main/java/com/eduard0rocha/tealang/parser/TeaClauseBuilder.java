package com.eduard0rocha.tealang.parser;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.eduard0rocha.tealang.data.clause.TeaClause;
import com.eduard0rocha.tealang.data.clause.TeaFact;
import com.eduard0rocha.tealang.data.clause.term.TeaAtom;
import com.eduard0rocha.tealang.data.clause.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.clause.term.TeaTerm;

/**
 * Converts ANTLR parse trees into TeaClause DTOs.
 */
public class TeaClauseBuilder extends TeaBaseVisitor<TeaClause> {

	@Override
	public TeaClause visitClause(final TeaParser.ClauseContext ctx) {
		return visit(ctx.fact());
	}
	
	@Override
	public TeaClause visitFact(final TeaParser.FactContext ctx) {
		final TeaTerm term = toTeaTerm(ctx.term());
		return new TeaFact(term);
	}
	
	private TeaTerm toTeaTerm(final TeaParser.TermContext ctx) {
		if (ctx.termArgs() == null) {
			return new TeaAtom(ctx.ATOM().getText());
		}
		final TeaParser.TermArgsContext termArgsCtx = ctx.termArgs();
		final List<String> arguments = termArgsCtx.ATOM().stream().map(TerminalNode::getText).toList();
		return new TeaCompoundTerm(ctx.ATOM().getText(), arguments);
	}
}
