package com.eduard0rocha.tealang.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.eduard0rocha.tealang.data.clause.TeaClause;
import com.eduard0rocha.tealang.parser.TeaParserFacade;

/**
 * CLI interface.
 */
public class CommandLineInterface {
	
	/**
	 * Prefix identifying a comment line in a .tea file.
	 */
	private static final String COMMENT_PREFIX = "%";

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
			final List<String> lines = Files.readAllLines(path);
			for (final String line : lines) {
				final String clause = line.trim();
				if (clause.isEmpty() || clause.startsWith(COMMENT_PREFIX)) {
					continue;
				}
				handleFileClause(clause);
				// TODO: confirmation message
			}
		} catch (final IOException e) {
			System.err.println("Failed to read file: " + filePath + " (" + e.getMessage() + ")");
		}
	}
	
	private void handleFileClause(final String clause) {
		final TeaClause parsedClause = TeaParserFacade.parse(clause);
		// TODO: handle parsedClause
	}
	
	/**
	 * Starts the CLI session.
	 */
	public void start() {
		// TODO
	}
}
