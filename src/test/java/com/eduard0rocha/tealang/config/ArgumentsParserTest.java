package com.eduard0rocha.tealang.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.ApplicationArguments;

class ArgumentsParserTest {
	
	@Test
    void parse_withNoArgs_returnsEmptyList() {
        final String[] args = new String[]{};
        final ApplicationArguments result = ArgumentsParser.parse(args);
        assertTrue(result.filePaths().isEmpty());
    }

    @Test
    void parse_withArgs_returnsFilePathsInOrder() {
        final String[] args = new String[]{"a.tea", "b.tea"};
        final ApplicationArguments result = ArgumentsParser.parse(args);
        assertEquals(List.of("a.tea", "b.tea"), result.filePaths());
    }
}
