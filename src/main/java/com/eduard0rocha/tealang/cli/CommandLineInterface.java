package com.eduard0rocha.tealang.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.misc.ParseCancellationException;

import com.eduard0rocha.tealang.data.FileLoadResult;
import com.eduard0rocha.tealang.data.language.query.TeaQuery;
import com.eduard0rocha.tealang.exception.InvalidQueryException;
import com.eduard0rocha.tealang.knowledgebase.KnowledgeBaseManager;
import com.eduard0rocha.tealang.parser.TeaParserFacade;
import com.eduard0rocha.tealang.resolution.Substitution;

/**
 * CLI interface.
 */
public class CommandLineInterface {
	
	private KnowledgeBaseManager knowledgeBaseManager = new KnowledgeBaseManager();
	
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static final String TEALANG_VERSION = "0.0.1-SNAPSHOT";
	private static final String PROMPT = "tea> ";
	private static final String MORE_SOLUTIONS_COMMAND = ";";
	private static final String EXIT_COMMAND = "exit";
	
	/**
	 * Loads the given Tea files into the knowledge base and starts the CLI session.
	 *
	 * @param filePaths the paths to the .tea files to load at startup
	 */
	public void start(final List<String> filePaths) {
		System.out.println("Tea-lang " + TEALANG_VERSION);
		runFiles(filePaths);
		runRepl();
	}
	
	private void runFiles(final List<String> filePaths) {
		final List<FileLoadResult> results = filePaths.stream()
	            .map(knowledgeBaseManager::loadFile)
	            .toList();
		printLoadSummary(results);
	}
	
	private void printLoadSummary(final List<FileLoadResult> results) {
	    final long successCount = results.stream().filter(FileLoadResult::success).count();
	    System.out.println(successCount + " of " + results.size() + " files loaded successfully.");
	    results.stream()
	            .filter(result -> !result.success())
	            .forEach(result -> System.err.println("Failed: " + result.filePath() + " (" + result.errorMessage() + ")"));
	}
	
	private void runRepl() {
	    while (true) {
	        System.out.print(PROMPT);
	        try {
	            final String query = reader.readLine();
	            if (query == null || query.equalsIgnoreCase(EXIT_COMMAND)) {
	                break;
	            }
	            if (query.isBlank()) {
	                continue;
	            }
	            handleQuery(query);
	        } catch (final ParseCancellationException | InvalidQueryException e) {
	            System.out.println("Invalid query: " + e.getMessage());
	        } catch (final IOException e) {
	            System.err.println("Failed to read query: " + e.getMessage());
	            break;
	        }
	    }
	}
	
	private void handleQuery(final String query) throws IOException {
	    final TeaQuery queryParsed = TeaParserFacade.parseQuery(query);
	    final Iterator<Substitution> solutions = knowledgeBaseManager.query(queryParsed.term());

	    if (!solutions.hasNext()) {
	        System.out.println("false.");
	        return;
	    }

	    while (solutions.hasNext()) {
	        final Substitution substitution = solutions.next();
	        final boolean hasMore = solutions.hasNext();
	        System.out.println(substitution.toPrologString() + (hasMore ? "" : "."));

	        if (!hasMore) {
	            break;
	        }

	        System.out.print("Continue? (" + MORE_SOLUTIONS_COMMAND + " for more) ");
	        final String answer = reader.readLine();
	        if (answer == null || !answer.equals(MORE_SOLUTIONS_COMMAND)) {
	            break;
	        }
	    }
	}
}
