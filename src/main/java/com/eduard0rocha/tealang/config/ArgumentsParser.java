package com.eduard0rocha.tealang.config;

import java.util.List;

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
		return new ApplicationArguments(List.of(args));
	}
}
