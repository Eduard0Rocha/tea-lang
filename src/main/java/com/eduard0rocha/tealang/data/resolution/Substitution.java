package com.eduard0rocha.tealang.data.resolution;

import java.util.HashMap;
import java.util.Map;

import com.eduard0rocha.tealang.data.language.term.TeaTerm;

/**
 * Tracks variable-to-term bindings discovered during unification.
 */
public class Substitution {
	
	private final Map<String, TeaTerm> bindings = new HashMap<>();
	
	// TODO: implement methods
}
