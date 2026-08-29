package com.eduard0rocha.tealang.resolution;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.eduard0rocha.tealang.data.language.term.TeaTerm;

/**
 * Tracks variable-to-term bindings discovered during unification.
 */
public class Substitution {
	
	private final Map<String, TeaTerm> bindings = new HashMap<>();
	
	/**
     * Binds the given variable name to a term.
     *
     * @param variableName the variable's name
     * @param term         the term to bind it to
     */
    public void bind(final String variableName, final TeaTerm term) {
        bindings.put(variableName, term);
    }
    
    /**
     * Checks whether this substitution has any bindings.
     *
     * @return {@code true} if empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return bindings.isEmpty();
    }
    
    /**
     * Returns the bindings discovered so far.
     *
     * @return the variable-to-term bindings
     */
    public Map<String, TeaTerm> bindings() {
        return bindings;
    }
    
    /**
     * Checks whether the given variable is already bound.
     *
     * @param variableName the variable's name
     * @return {@code true} if bound, {@code false} otherwise
     */
    public boolean isBound(final String variableName) {
        return bindings.containsKey(variableName);
    }

    /**
     * Returns the term bound to the given variable.
     *
     * @param variableName the variable's name
     * @return the bound term
     */
    public TeaTerm get(final String variableName) {
        return bindings.get(variableName);
    }
    
    /**
     * Formats this substitution as a Tea-style response (e.g. {@code X = alice.} or {@code true.}).
     *
     * @return the formatted substitution
     */
    public String toPrologString() {
        if (isEmpty()) {
            return "true";
        }
        return bindings.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue().toPrologString())
                .collect(Collectors.joining(",\n"));
    }
}
