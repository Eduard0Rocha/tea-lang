package com.eduard0rocha.tealang.cli;

import java.util.List;

import com.eduard0rocha.tealang.data.FileLoadResult;
import com.eduard0rocha.tealang.knowledgebase.KnowledgeBaseManager;

/**
 * CLI interface.
 */
public class CommandLineInterface {
	
	private KnowledgeBaseManager knowledgeBaseManager = new KnowledgeBaseManager();
	
	/**
	 * Loads the Tea programs from the given files into the knowledge base.
	 *
	 * @param filePaths the paths to the .tea files
	 */
	public void runFiles(final List<String> filePaths) {
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
	
	/**
	 * Starts the CLI session.
	 */
	public void start() {
		// TODO
	}
}
