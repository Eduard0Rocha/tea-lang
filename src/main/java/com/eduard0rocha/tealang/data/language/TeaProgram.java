package com.eduard0rocha.tealang.data.language;

import java.util.List;

import com.eduard0rocha.tealang.data.language.clause.TeaClause;

/**
 * Tea program DTO.
 * 
 * @param clauses the program's list of clauses
 */
public record TeaProgram(List<TeaClause> clauses) {

}
