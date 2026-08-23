package com.eduard0rocha.tealang.data.clause.term;

import java.util.List;

/**
 * Tea compound term DTO.
 *
 * @param functor   the compound term's functor
 * @param arguments the compound term's arguments
 */
public record TeaCompoundTerm(String functor, List<String> arguments) implements TeaTerm {

}
