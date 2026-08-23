package com.eduard0rocha.tealang.parser;

import com.eduard0rocha.tealang.data.clause.TeaClause;

/**
 * Tea parser facade.
 * Wraps ANTLR lexer/parser setup so callers don't depend on ANTLR directly.
 */
public class TeaParserFacade {

	public TeaParserFacade() {
	}
	
	/**
     * Parses the given Tea clause and returns the corresponding TeaClause DTO.
     *
     * @param clause the raw Tea clause text
     * @return the parsed TeaClause
     * @throws org.antlr.v4.runtime.misc.ParseCancellationException if the command is invalid
     */
    public static TeaClause parse(final String clause) {
    	// TODO
    	return null;
    }
}
