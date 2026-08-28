package com.eduard0rocha.tealang.data.resolution;

/**
 * Result of a knowledge base query DTO.
 *
 * @param success      whether the query unified with some clause
 */
public record QueryResult(boolean success) {
	
}
