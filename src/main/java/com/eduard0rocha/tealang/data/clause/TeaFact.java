package com.eduard0rocha.tealang.data.clause;

import com.eduard0rocha.tealang.data.clause.term.TeaTerm;

/**
 * Tea fact clause DTO.
 * 
 * @param term the fact's term
 */
public record TeaFact(TeaTerm term) implements TeaClause {

}
