package com.eduard0rocha.tealang.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

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
    	final TeaLexer lexer = new TeaLexer(CharStreams.fromString(clause));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        
        final CommonTokenStream tokens = new CommonTokenStream(lexer);

        final TeaParser parser = new TeaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        final TeaParser.ClauseContext tree = parser.clause();
        
        System.out.println(tree);
    	
    	// TODO: build TeaClause
    	return null;
    }
}
