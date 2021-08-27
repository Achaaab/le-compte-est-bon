package com.github.achaaab.lceb.modele.resolution.manuelle;

import com.github.achaaab.lceb.modele.resolution.Operateur;
import com.github.achaaab.lceb.modele.resolution.OperationInterdite;
import com.github.achaaab.lceb.modele.resolution.Solution;
import com.github.achaaab.lceb.presentation.PresentationArdoise;

import static com.github.achaaab.lceb.modele.Tirage.NOMBRE_PLAQUES;

/**
 * L'ardoise permet de saisir une solution. Pour ce faire, elle offre des fonctions basiques
 * (écriture d'un nombre, d'un opérateur, effacement du dernier élément écrit...).
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Ardoise {

	private static final int ETAPE_OPERANDE_GAUCHE = 0;
	private static final int ETAPE_OPERATEUR = 1;
	private static final int ETAPE_OPERANDE_DROITE = 2;

	private final ElementCalcul[][] elementsCalcul;
	private final Solution solution;
	private final PresentationArdoise presentation;

	private int nombreElementsCalcul;
	private Operande operandeGauche;
	private Operateur operateur;
	private Operande operandeDroite;

	/**
	 * @since 0.0.0
	 */
	public Ardoise() {

		elementsCalcul = new ElementCalcul[NOMBRE_PLAQUES - 1][3];
		solution = new Solution();
		nombreElementsCalcul = 0;
		presentation = new PresentationArdoise(this);
	}

	/**
	 * @since 0.0.0
	 */
	public void effacer() {

		while (nombreElementsCalcul > 0) {
			effacerElementCalcul();
		}
	}

	/**
	 * efface le dernier élément de calcul
	 *
	 * @since 0.0.0
	 */
	public void effacerElementCalcul() {

		var rangCalcul = nombreElementsCalcul / 3;
		var etapeCalcul = nombreElementsCalcul % 3;

		switch (etapeCalcul) {

			case ETAPE_OPERANDE_GAUCHE:

				/*
				 * on est à l'étape de l'opérande gauche, on doit donc revenir à la ligne du dessus,
				 * supprimer le résultat et l'opérande droite
				 */

				if (rangCalcul > 0) {

					// on recharge la ligne du dessus

					operandeGauche = (Operande) elementsCalcul[rangCalcul - 1][ETAPE_OPERANDE_GAUCHE];
					operateur = (Operateur) elementsCalcul[rangCalcul - 1][ETAPE_OPERATEUR];
					operandeDroite = (Operande) elementsCalcul[rangCalcul - 1][ETAPE_OPERANDE_DROITE];

					// on indique que la plaque gauche est utilisée

					var plaqueGauche = operandeGauche.getPlaque();
					plaqueGauche.setUtilisee(true);
					plaqueGauche.setNombre(operandeGauche.getNombre());

					// on indique que la plaque droite n'est plus utilisée

					var plaqueDroite = operandeDroite.getPlaque();
					plaqueDroite.setUtilisee(false);

					// on supprime la dernière operation

					solution.retirerOperation();

					nombreElementsCalcul--;
				}

				break;

			case ETAPE_OPERATEUR:

				// on est à l'étape de l'opérateur, on doit donc indiquer que la plaque de gauche n'est plus utilisée

				var plaqueGauche = operandeGauche.getPlaque();
				plaqueGauche.setUtilisee(false);

				nombreElementsCalcul--;
				break;

			case ETAPE_OPERANDE_DROITE:

				// on revient simplement sur l'opérateur
				nombreElementsCalcul--;
				break;

			default:

				// situation impossible
				break;
		}

		presentation.actualiser();
	}

	/**
	 * @param elementCalcul
	 * @throws ElementCalculInterdit
	 * @throws OperationInterdite
	 * @since 0.0.0
	 */
	public void ajouterElementCalcul(ElementCalcul elementCalcul) throws ElementCalculInterdit, OperationInterdite {

		// le rang du calcul correspond à la ligne en cours, de 0 à 4
		var rangCalcul = nombreElementsCalcul / 3;

		/*
		 * l'étape du calcul correspond à la colonne en cours, de 0 à 2
		 *
		 * 0 : opérande gauche
		 * 1 : opérateur
		 * 2 : opérande droite
		 */

		var etapeCalcul = nombreElementsCalcul % 3;

		// selon l'étape en cours, on accepte ou non l'élément de calcul, et on agit en conséquence

		switch (etapeCalcul) {

			case ETAPE_OPERANDE_GAUCHE:

				// si l'élément de calcul est une opérande, on l'enregistre, sinon, on lève une exception

				if (elementCalcul instanceof Operande) {
					operandeGauche = (Operande) elementCalcul;
				} else {
					throw new ElementCalculInterdit("Une opérande gauche est attendue.");
				}

				break;

			case ETAPE_OPERATEUR:

				// si l'élément de calcul est un opérateur, on l'enregistre, sinon, on lève une exception

				if (elementCalcul instanceof Operateur) {
					operateur = (Operateur) elementCalcul;
				} else {
					throw new ElementCalculInterdit("Un opérateur est attendu.");
				}

				break;

			case ETAPE_OPERANDE_DROITE:

				// si l'élément de calcul est une opérande, on l'enregistre, sinon, on lève une exception

				if (elementCalcul instanceof Operande) {

					operandeDroite = (Operande) elementCalcul;

					// on crée une opération à partir de l'opérateur et des deux opérandes
					var operation = operateur.getOperation(operandeGauche, operandeDroite);
					solution.ajouterOperation(operation);

					var resultat = operation.getResultat();

					// s'il reste encore une operation possible, on restitue le résultat dans une plaque

					if (rangCalcul < NOMBRE_PLAQUES - 2) {

						var plaqueOperandeGauche = operandeGauche.getPlaque();
						plaqueOperandeGauche.reinitialiser(resultat);
					}

				} else {

					throw new ElementCalculInterdit("Une opérande droite attendue.");
				}

				break;

			default:

				// situation impossible
				break;
		}

		// on enregistre l'élément de calcul afin de pouvoir revenir dessus en cas d'effacement

		elementsCalcul[rangCalcul][etapeCalcul] = elementCalcul;
		nombreElementsCalcul++;

		presentation.actualiser();
	}

	/**
	 * @return nombre d'éléments de calcul utilisés
	 * @since 0.0.0
	 */
	public int getNombreElementsCalcul() {
		return nombreElementsCalcul;
	}

	/**
	 * @return opérande gauche de la ligne en cours
	 * @since 0.0.0
	 */
	public Operande getOperandeGauche() {
		return operandeGauche;
	}

	/**
	 * @return opérateur de la ligne en cours
	 * @since 0.0.0
	 */
	public Operateur getOperateur() {
		return operateur;
	}

	/**
	 * @return opérande droite de la ligne en cours
	 * @since 0.0.0
	 */
	public Operande getOperandeDroite() {
		return operandeDroite;
	}

	/**
	 * @return solution en cours
	 * @since 0.0.0
	 */
	public Solution getSolution() {
		return solution;
	}

	/**
	 * @return présentation de l'ardoise
	 * @since 0.0.0
	 */
	public PresentationArdoise getPresentation() {
		return presentation;
	}
}