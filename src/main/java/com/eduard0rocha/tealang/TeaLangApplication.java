package com.eduard0rocha.tealang;

import com.eduard0rocha.tealang.cli.CommandLineInterface;
import com.eduard0rocha.tealang.config.ArgumentsParser;
import com.eduard0rocha.tealang.data.ApplicationArguments;

/**
 * Entry point of tea-lang application.
 */
public class TeaLangApplication {

	public static void main(String[] args) {
		// Parse application arguments
		final ApplicationArguments applicationArguments = ArgumentsParser.parse(args);

		// Start CLI session
		final CommandLineInterface cli = new CommandLineInterface();
		cli.runFiles(applicationArguments.filePaths());
		cli.start();
	}
}
