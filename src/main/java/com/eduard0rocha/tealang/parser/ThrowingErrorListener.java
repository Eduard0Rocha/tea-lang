package com.eduard0rocha.tealang.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * Error listener that throws an exception on syntax errors,
 * instead of only printing to the console (ANTLR's default behavior).
 */
public class ThrowingErrorListener extends BaseErrorListener {

    public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer,
                             final Object offendingSymbol,
                             final int line,
                             final int charPositionInLine,
                             final String msg,
                             final RecognitionException e) {
        throw new ParseCancellationException(
            "Syntax error in line " + line + ":" + charPositionInLine + " - " + msg);
    }
}