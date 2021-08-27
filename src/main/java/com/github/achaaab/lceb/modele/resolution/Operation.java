package com.github.achaaab.lceb.modele.resolution;

/**
 * Une opération est une ligne de calcul, elle comprend deux opérandes et un opérateur.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public abstract class Operation {

	private final char operateur;

	protected int operandeGauche;
	protected int operandeDroite;

	public int resultat;

	/**
	 * @param operateur
	 * @since 0.0.0
	 */
	public Operation(char operateur) {
		this.operateur = operateur;
	}

	/**
	 * @param operateur
	 * @param operandeGauche
	 * @param operandeDroite
	 */
	public Operation(char operateur, int operandeGauche, int operandeDroite) {

		this.operateur = operateur;
		this.operandeGauche = operandeGauche;
		this.operandeDroite = operandeDroite;

		calculer();
	}

	/**
	 * @throws OperationInterdite
	 * @since 0.0.0
	 */
	public abstract void verifier() throws OperationInterdite;

	/**
	 * @since 0.0.0
	 */
	public abstract void calculer();

	/**
	 * @return
	 * @since 0.0.0
	 */
	public int getResultat() {
		return resultat;
	}

	@Override
	public String toString() {
		return operandeGauche + " " + operateur + " " + operandeDroite + " = " + resultat;
	}
}