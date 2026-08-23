package com.eduard0rocha.tealang.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.eduard0rocha.tealang.data.FileLoadResult;
import com.eduard0rocha.tealang.knowledgebase.KnowledgeBaseManager;

/**
 * CLI interface.
 */
public class CommandLineInterface {
	
	private KnowledgeBaseManager knowledgeBaseManager = new KnowledgeBaseManager();
	
	private static final String TEALANG_VERSION = "0.0.1-SNAPSHOT";
	private static final String PROMPT = "tea> ";
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
	    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
	        } catch (final IOException e) {
	            System.err.println("Failed to read query: " + e.getMessage());
	            break;
	        }
	    }
	}
	
	private void handleQuery(final String query) {
		// TODO
	}
}
