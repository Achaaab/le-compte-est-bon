package com.github.achaaab.lceb.modele.resolution;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Addition extends Operation {

	private static final char OPERATEUR = '+';

	/**
	 * @param operandeGauche
	 * @param operandeDroite
	 * @since 0.0.0
	 */
	public Addition(int operandeGauche, int operandeDroite) {
		super(OPERATEUR, operandeGauche, operandeDroite);
	}

	@Override
	public void calculer() {
		resultat = operandeGauche + operandeDroite;
	}

	@Override
	public void verifier() {

	}
}