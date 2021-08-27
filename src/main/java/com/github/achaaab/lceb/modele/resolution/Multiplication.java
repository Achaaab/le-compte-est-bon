package com.github.achaaab.lceb.modele.resolution;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Multiplication extends Operation {

	private static final char OPERATEUR = 'x';

	/**
	 * @param operandeGauche
	 * @param operandeDroite
	 * @since 0.0.0
	 */
	public Multiplication(int operandeGauche, int operandeDroite) {
		super(OPERATEUR, operandeGauche, operandeDroite);
	}

	@Override
	public void calculer() {
		resultat = operandeGauche * operandeDroite;
	}

	@Override
	public void verifier() throws OperationInterdite {

		if (operandeGauche == 1 || operandeDroite == 1) {
			throw new OperationInterdite("La multiplication par 1 est inutile.");
		}
	}
}