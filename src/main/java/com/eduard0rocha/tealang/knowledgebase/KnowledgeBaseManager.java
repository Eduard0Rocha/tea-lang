package com.eduard0rocha.tealang.knowledgebase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import com.eduard0rocha.tealang.data.FileLoadResult;
import com.eduard0rocha.tealang.data.TeaProgram;
import com.eduard0rocha.tealang.data.clause.TeaClause;
import com.eduard0rocha.tealang.parser.TeaParserFacade;

/**
 * Knowledge base manager.
 */
public class KnowledgeBaseManager {
	
	private KnowledgeBase knowledgeBase = new KnowledgeBase();

	/**
	 * Reads, parses, and loads the Tea program from the given file into the knowledge base.
	 *
	 * @param filePath the path to a .tea file
	 * @return the result of the load operation
	 */
	public FileLoadResult loadFile(final String filePath) {
		final Path path = Path.of(filePath);
		try {
			final String content = Files.readString(path);
	        handleFileProgram(content);
	        return new FileLoadResult(filePath, true, null);
		} catch (final NoSuchFileException e) {
		    return new FileLoadResult(filePath, false, "File not found");
		} catch (final IOException e) {
		    return new FileLoadResult(filePath, false, "Error reading file");
		}
	}
	
	private void handleFileProgram(final String program) {
	    final TeaProgram parsedProgram = TeaParserFacade.parseProgram(program);
	    for (final TeaClause clause : parsedProgram.clauses()) {
	    	knowledgeBase.addClause(clause);
	    }
	}
}
