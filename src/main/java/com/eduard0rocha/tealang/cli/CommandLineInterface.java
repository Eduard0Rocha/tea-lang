package com.eduard0rocha.tealang.cli;

import com.eduard0rocha.tealang.knowledgebase.KnowledgeBaseManager;

/**
 * CLI interface.
 */
public class CommandLineInterface {
	
	/**
	 * Knowledge base manager.
	 */
	private KnowledgeBaseManager knowledgeBaseManager = new KnowledgeBaseManager();;

	/**
	 * Loads the Tea program from the given file into the knowledge base.
	 *
	 * @param filePath the path to a .tea file
	 */
	public void runFile(final String filePath) {
		knowledgeBaseManager.loadFile(filePath);
	}
	
	/**
	 * Starts the CLI session.
	 */
	public void start() {
		// TODO
	}
}
