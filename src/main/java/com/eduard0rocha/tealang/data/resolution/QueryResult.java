package com.eduard0rocha.tealang.data.resolution;

/**
 * Result of a knowledge base query DTO.
 *
 * @param success      whether the query unified with some clause
 * @param substitution the variable bindings discovered, if successful
 */
public record QueryResult(boolean success, Substitution substitution) {

}
