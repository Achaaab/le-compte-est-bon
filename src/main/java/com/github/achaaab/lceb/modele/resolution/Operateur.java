package com.github.achaaab.lceb.modele.resolution;

import com.github.achaaab.lceb.modele.resolution.manuelle.ElementCalcul;
import com.github.achaaab.lceb.modele.resolution.manuelle.Operande;

/**
 * Un opérateur permet de lier 2 plaques et d'obtenir un résultat.
 *
 * @author Jonathan Guéhenneux
 */
public enum Operateur implements ElementCalcul {

	PLUS("+") {
		@Override
		public Operation getOperation(Operande operandeGauche, Operande operandeDroite) {

			var operation = new Addition(operandeGauche.getNombre(), operandeDroite.getNombre());
			operation.verifier();
			return operation;
		}
	},

	MOINS("-") {
		@Override
		public Operation getOperation(Operande operandeGauche, Operande operandeDroite) throws OperationInterdite {

			var operation = new Soustraction(operandeGauche.getNombre(), operandeDroite.getNombre());
			operation.verifier();
			return operation;
		}
	},

	MULTIPLIE("x") {
		@Override
		public Operation getOperation(Operande operandeGauche, Operande operandeDroite) throws OperationInterdite {

			var operation = new Multiplication(operandeGauche.getNombre(), operandeDroite.getNombre());
			operation.verifier();
			return operation;
		}
	},

	DIVISE("/") {
		@Override
		public Operation getOperation(Operande operandeGauche, Operande operandeDroite) throws OperationInterdite {

			var operation = new Division(operandeGauche.getNombre(), operandeDroite.getNombre());
			operation.verifier();
			return operation;
		}
	};

	private final String signe;

	/**
	 * @param signe
	 * @since 0.0.0
	 */
	Operateur(String signe) {
		this.signe = signe;
	}

	/**
	 * @param operandeGauche
	 * @param operandeDroite
	 * @return
	 * @throws OperationInterdite
	 * @since 0.0.0
	 */
	public abstract Operation getOperation(Operande operandeGauche, Operande operandeDroite) throws OperationInterdite;

	@Override
	public String toString() {
		return ' ' + signe + ' ';
	}
}