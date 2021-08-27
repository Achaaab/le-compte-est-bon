package com.github.achaaab.lceb.modele.resolution;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Division extends Operation {

	private static final char OPERATEUR = '/';

	/**
	 * @param operandeGauche
	 * @param operandeDroite
	 * @since 0.0.0
	 */
	public Division(int operandeGauche, int operandeDroite) {
		super(OPERATEUR, operandeGauche, operandeDroite);
	}

	@Override
	public void calculer() {
		resultat = operandeGauche / operandeDroite;
	}

	@Override
	public void verifier() throws OperationInterdite {

		if (operandeDroite == 0) {
			throw new OperationInterdite("La division par 0 est interdite.");
		}

		if (operandeGauche % operandeDroite != 0) {
			throw new OperationInterdite(operandeGauche + " n'est pas divisible par " + operandeDroite + '.');
		}

		if (operandeGauche == 0) {
			throw new OperationInterdite("La division de 0 est inutile.");
		}

		if (operandeDroite == 1) {
			throw new OperationInterdite("La division par 1 est inutile.");
		}

		if (operandeDroite * operandeDroite == operandeGauche) {
			throw new OperationInterdite("La division d'un nombre carré par sa racine est inutile.");
		}
	}
}