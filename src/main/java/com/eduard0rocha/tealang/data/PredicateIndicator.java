package com.eduard0rocha.tealang.data;

/**
 * Predicate indicator DTO.
 * Identifies a predicate by its functor and arity, e.g. {@code father/2}.
 * 
 * @param functor the predicate's functor
 * @param arity   the predicate's arity (number of arguments)
 */
public record PredicateIndicator(String functor, int arity) {

}
