package com.eduard0rocha.tealang.config;

import com.eduard0rocha.tealang.data.ApplicationArguments;

/**
 * Application arguments parser.
 */
public class ArgumentsParser {
	
	/**
	 * Parses application arguments.
	 *
	 * @param args application arguments
	 * @return parsed application arguments
	 */
	public static ApplicationArguments parse(final String[] args) {
		return new ApplicationArguments(args.length > 0 ? args[0] : null);
	}
}
