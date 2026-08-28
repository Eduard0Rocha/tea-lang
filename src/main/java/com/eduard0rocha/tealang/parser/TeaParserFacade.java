package com.eduard0rocha.tealang.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.eduard0rocha.tealang.data.language.TeaProgram;
import com.eduard0rocha.tealang.data.language.query.TeaQuery;

/**
 * Tea parser facade.
 * Wraps ANTLR lexer/parser setup so callers don't depend on ANTLR directly.
 */
public class TeaParserFacade {

	/**
     * Parses the given Tea program and returns the corresponding TeaProgram DTO.
     *
     * @param clause the raw Tea program text
     * @return the parsed TeaProgram
     * @throws org.antlr.v4.runtime.misc.ParseCancellationException if the program is invalid
     */
    public static TeaProgram parseProgram(final String program) {
    	final TeaParser parser = createParser(program);
        final TeaParser.ProgramContext tree = parser.program();
    	return new TeaProgramBuilder().visit(tree);
    }
    
    /**
	 * Parses the given Tea query and returns the corresponding TeaQuery DTO.
	 *
	 * @param query the raw Tea query text
	 * @return the parsed TeaQuery
	 * @throws org.antlr.v4.runtime.misc.ParseCancellationException if the query is invalid
	 */
    public static TeaQuery parseQuery(final String query) {
    	final TeaParser parser = createParser(query);
    	final TeaParser.ClauseContext tree = parser.clause();
    	return new TeaProgramBuilder().toTeaQuery(tree);
    }
    
    private static TeaParser createParser(final String input) {
		final TeaLexer lexer = new TeaLexer(CharStreams.fromString(input));
		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final TeaParser parser = new TeaParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);
		return parser;
	}
}
