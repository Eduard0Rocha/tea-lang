package com.eduard0rocha.tealang;

import com.eduard0rocha.tealang.config.ArgumentsParser;
import com.eduard0rocha.tealang.data.ApplicationArguments;

public class TeaLangApplication {

	public static void main(String[] args) {
		// Parse application arguments
		final ApplicationArguments applicationArguments = ArgumentsParser.parse(args);
		
		System.out.println(applicationArguments);
		
		// Validate application arguments
		// TODO
		
		// Start CLI session
		// TODO
	}

}
