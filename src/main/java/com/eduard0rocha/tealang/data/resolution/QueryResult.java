package com.eduard0rocha.tealang.data.resolution;

import java.util.stream.Collectors;

/**
 * Result of a knowledge base query DTO.
 *
 * @param success      whether the query unified with some clause
 * @param substitution the variable bindings discovered, if successful
 */
public record QueryResult(boolean success, Substitution substitution) {
	
	/**
     * Formats this result as a Tea-style response.
     *
     * @return the formatted result
     */
    public String toPrologString() {
        if (!success) {
            return "false.";
        }
        if (substitution.isEmpty()) {
            return "true.";
        }
        return substitution.bindings().entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue().toPrologString())
                .collect(Collectors.joining("\n")) + ".";
    }
}
