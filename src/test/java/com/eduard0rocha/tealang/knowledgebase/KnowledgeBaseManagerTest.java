package com.eduard0rocha.tealang.knowledgebase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.eduard0rocha.tealang.data.FileLoadResult;
import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.resolution.Substitution;

class KnowledgeBaseManagerTest {
	
	@TempDir
	Path tempDir;

	@Test
	void loadFile_withValidFact_addsClauseToKnowledgeBase() throws IOException {
		final Path file = tempDir.resolve("valid.tea");
		Files.writeString(file, "rains.");
		final KnowledgeBaseManager manager = new KnowledgeBaseManager();

		final FileLoadResult result = manager.loadFile(file.toString());

		assertTrue(result.success());
		final Iterator<Substitution> queryResult = manager.query(new TeaAtom("rains"));
		assertTrue(queryResult.hasNext());
	}
	
	@Test
	void loadFile_withNonExistentFile_returnsFailureResult() {
		final KnowledgeBaseManager manager = new KnowledgeBaseManager();

		final FileLoadResult result = manager.loadFile("nonexistent.tea");

		assertFalse(result.success());
		assertEquals("File not found", result.errorMessage());
	}

	@Test
	void loadFile_withVariableInFact_returnsFailureResult() throws IOException {
		final Path file = tempDir.resolve("invalid.tea");
		Files.writeString(file, "rains(X).");
		final KnowledgeBaseManager manager = new KnowledgeBaseManager();

		final FileLoadResult result = manager.loadFile(file.toString());

		assertFalse(result.success());
	}
}
