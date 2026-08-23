package com.eduard0rocha.tealang.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.eduard0rocha.tealang.data.TeaProgram;
import com.eduard0rocha.tealang.data.clause.TeaClause;
import com.eduard0rocha.tealang.parser.TeaParserFacade;

/**
 * CLI interface.
 */
public class CommandLineInterface {

	public CommandLineInterface() {
		// TODO: instantiate the "KnowledgeBaseManager"
	}
	
	/**
	 * Reads and executes each Tea clause from the given file, one per line.
	 * Blank lines and lines starting with {@code %} are skipped.
	 *
	 * @param filePath the path to a .tea file
	 */
	public void runFile(final String filePath) {
		final Path path = Path.of(filePath);
		try {
			final String content = Files.readString(path);
	        handleFileProgram(content);
	        // TODO: confirmation message
		} catch (final IOException e) {
			System.err.println("Failed to read file: " + filePath + " (" + e.getMessage() + ")");
		}
	}
	
	private void handleFileProgram(final String program) {
	    System.out.println(program);
	    final TeaProgram parsedProgram = TeaParserFacade.parseProgram(program);
	    System.out.println(parsedProgram);
	    // TODO: handle parsedProgram
	}
	
	/**
	 * Starts the CLI session.
	 */
	public void start() {
		// TODO
	}
}
