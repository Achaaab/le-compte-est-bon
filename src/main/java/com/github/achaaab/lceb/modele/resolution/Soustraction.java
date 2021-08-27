package com.github.achaaab.lceb.modele.resolution;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Soustraction extends Operation {

	private static final char OPERATEUR = '-';

	/**
	 * @param operandeGauche
	 * @param operandeDroite
	 * @since 0.0.0
	 */
	public Soustraction(int operandeGauche, int operandeDroite) {
		super(OPERATEUR, operandeGauche, operandeDroite);
	}

	@Override
	public void calculer() {
		resultat = operandeGauche - operandeDroite;
	}

	@Override
	public void verifier() throws OperationInterdite {

		if (operandeGauche <= operandeDroite) {
			throw new OperationInterdite("Le résultat doit être strictement positif.");
		}
	}
}