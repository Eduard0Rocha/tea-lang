package com.eduard0rocha.tealang.resolution;

import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;

/**
 * Performs unification between two Tea terms.
 */
public final class Unifier {
	
	/**
	 * Attempts to unify two terms, recording any variable bindings discovered in the given substitution.
	 *
	 * @param a            the first term
	 * @param b            the second term
	 * @param substitution the substitution to record bindings in
	 * @return {@code true} if the terms unify, {@code false} otherwise
	 */
	public static boolean unify(final TeaTerm a, final TeaTerm b, final Substitution substitution) {
		if (a instanceof TeaVariable aVariable) {
	        if (substitution.isBound(aVariable.name())) {
	            return unify(substitution.get(aVariable.name()), b, substitution);
	        }
	        substitution.bind(aVariable.name(), b);
	        return true;
	    }
	    if (b instanceof TeaVariable bVariable) {
	        if (substitution.isBound(bVariable.name())) {
	            return unify(a, substitution.get(bVariable.name()), substitution);
	        }
	        substitution.bind(bVariable.name(), a);
	        return true;
	    }
        if (a instanceof TeaAtom aAtom && b instanceof TeaAtom bAtom) {
        	return aAtom.name().equals(bAtom.name());
        }
        if (a instanceof TeaCompoundTerm aCompoundTerm && b instanceof TeaCompoundTerm bCompoundTerm) {
        	if (!aCompoundTerm.functor().equals(bCompoundTerm.functor())) {
				return false;
			}
        	if (aCompoundTerm.arguments().size() != bCompoundTerm.arguments().size()) {
				return false;
			}
        	for (int i = 0; i < aCompoundTerm.arguments().size(); i++) {
				if (!unify(aCompoundTerm.arguments().get(i), bCompoundTerm.arguments().get(i), substitution)) {
					return false;
				}
			}
			return true;
        }
        return false;
    }
}
