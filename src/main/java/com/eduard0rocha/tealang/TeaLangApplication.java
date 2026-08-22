package com.eduard0rocha.tealang;

import com.eduard0rocha.tealang.config.ArgumentsParser;
import com.eduard0rocha.tealang.data.ApplicationArguments;

/**
 * Entry point of tea-lang application.
 */
public class TeaLangApplication {

	public static void main(String[] args) {
		// Parse application arguments
		final ApplicationArguments applicationArguments = ArgumentsParser.parse(args);
		
		// Validate application arguments
		if (applicationArguments.filePath() == null || applicationArguments.filePath().isBlank()) {
			// TODO: display error message
			return;
		}
		
		// Start CLI session
		// TODO
	}

}
