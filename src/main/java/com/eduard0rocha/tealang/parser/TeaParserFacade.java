package com.eduard0rocha.tealang.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.eduard0rocha.tealang.data.TeaProgram;

/**
 * Tea parser facade.
 * Wraps ANTLR lexer/parser setup so callers don't depend on ANTLR directly.
 */
public class TeaParserFacade {

	public TeaParserFacade() {
	}
	
	/**
     * Parses the given Tea program and returns the corresponding TeaProgram DTO.
     *
     * @param clause the raw Tea program text
     * @return the parsed TeaProgram
     * @throws org.antlr.v4.runtime.misc.ParseCancellationException if the command is invalid
     */
    public static TeaProgram parseProgram(final String program) {
    	final TeaLexer lexer = new TeaLexer(CharStreams.fromString(program));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        
        final CommonTokenStream tokens = new CommonTokenStream(lexer);

        final TeaParser parser = new TeaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        final TeaParser.ProgramContext tree = parser.program();
        
    	return new TeaClauseBuilder().visit(tree);
    }
}
