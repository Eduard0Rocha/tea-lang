package com.eduard0rocha.tealang.resolution;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.eduard0rocha.tealang.data.language.clause.TeaClause;
import com.eduard0rocha.tealang.data.language.term.TeaTerm;

/**
 * Iterates lazily over successful unifications between a term and a list of candidate clauses.
 */
public class SolutionIterator implements Iterator<Substitution> {

    private final TeaTerm term;
    private final List<TeaClause> candidates;
    private int index = 0;
    private Substitution nextSolution = null;

    public SolutionIterator(final TeaTerm term, final List<TeaClause> candidates) {
        this.term = term;
        this.candidates = candidates;
    }

    @Override
    public boolean hasNext() {
        if (nextSolution != null) {
            return true;
        }
        while (index < candidates.size()) {
            final TeaClause candidate = candidates.get(index);
            index++;
            final Substitution substitution = new Substitution();
            if (Unifier.unify(term, candidate.head(), substitution)) {
                nextSolution = substitution;
                return true;
            }
        }
        return false;
    }

    @Override
    public Substitution next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        final Substitution result = nextSolution;
        nextSolution = null;
        return result;
    }
}