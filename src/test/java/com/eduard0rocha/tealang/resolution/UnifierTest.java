package com.eduard0rocha.tealang.resolution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.eduard0rocha.tealang.data.language.term.TeaAtom;
import com.eduard0rocha.tealang.data.language.term.TeaCompoundTerm;
import com.eduard0rocha.tealang.data.language.term.TeaVariable;

class UnifierTest {

	@Test
	void unify_withEqualAtoms_returnsTrueWithoutBindings() {
		final Substitution substitution = new Substitution();
		final boolean result = Unifier.unify(new TeaAtom("bob"), new TeaAtom("bob"), substitution);
		assertTrue(result);
		assertTrue(substitution.isEmpty());
	}

	@Test
	void unify_withDifferentAtoms_returnsFalse() {
		final Substitution substitution = new Substitution();
		final boolean result = Unifier.unify(new TeaAtom("bob"), new TeaAtom("alice"), substitution);
		assertFalse(result);
	}

	@Test
	void unify_variableWithAtom_bindsVariable() {
		final Substitution substitution = new Substitution();
		final boolean result = Unifier.unify(new TeaVariable("X"), new TeaAtom("bob"), substitution);
		assertTrue(result);
		assertEquals(new TeaAtom("bob"), substitution.get("X"));
	}

	@Test
	void unify_boundVariableWithCompatibleValue_returnsTrue() {
		final Substitution substitution = new Substitution();
		substitution.bind("X", new TeaAtom("bob"));
		final boolean result = Unifier.unify(new TeaVariable("X"), new TeaAtom("bob"), substitution);
		assertTrue(result);
	}

	@Test
	void unify_sameVariableTwiceWithDifferentValues_returnsFalse() {
		final Substitution substitution = new Substitution();
		final TeaCompoundTerm a = new TeaCompoundTerm("father", List.of(new TeaVariable("X"), new TeaVariable("X")));
		final TeaCompoundTerm b = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob")));

		final boolean result = Unifier.unify(a, b, substitution);

		assertFalse(result);
	}

	@Test
	void unify_compoundTermsWithDifferentFunctor_returnsFalse() {
		final Substitution substitution = new Substitution();
		final TeaCompoundTerm a = new TeaCompoundTerm("father", List.of(new TeaAtom("tom")));
		final TeaCompoundTerm b = new TeaCompoundTerm("mother", List.of(new TeaAtom("tom")));

		final boolean result = Unifier.unify(a, b, substitution);

		assertFalse(result);
	}

	@Test
	void unify_compoundTermsWithDifferentArity_returnsFalse() {
		final Substitution substitution = new Substitution();
		final TeaCompoundTerm a = new TeaCompoundTerm("color", List.of(new TeaAtom("green")));
		final TeaCompoundTerm b = new TeaCompoundTerm("color", List.of(new TeaAtom("green"), new TeaAtom("red")));

		final boolean result = Unifier.unify(a, b, substitution);

		assertFalse(result);
	}

	@Test
	void unify_compoundTermsWithMatchingArguments_bindsVariables() {
		final Substitution substitution = new Substitution();
		final TeaCompoundTerm a = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaVariable("X")));
		final TeaCompoundTerm b = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob")));

		final boolean result = Unifier.unify(a, b, substitution);

		assertTrue(result);
		assertEquals(new TeaAtom("bob"), substitution.get("X"));
	}

	@Test
	void unify_compoundTermsWithOneArgumentFailing_returnsFalse() {
		final Substitution substitution = new Substitution();
		final TeaCompoundTerm a = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("bob")));
		final TeaCompoundTerm b = new TeaCompoundTerm("father", List.of(new TeaAtom("tom"), new TeaAtom("alice")));

		final boolean result = Unifier.unify(a, b, substitution);

		assertFalse(result);
	}
}