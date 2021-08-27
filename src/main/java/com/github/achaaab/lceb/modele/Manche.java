package com.github.achaaab.lceb.modele;

import com.github.achaaab.lceb.modele.resolution.Solution;

import static com.github.achaaab.lceb.modele.ResultatManche.GAGNEE_PLUS_COURTE;
import static com.github.achaaab.lceb.modele.ResultatManche.GAGNEE_PLUS_PROCHE;
import static com.github.achaaab.lceb.modele.ResultatManche.NULLE;
import static com.github.achaaab.lceb.modele.ResultatManche.PERDUE_ABANDONNEE;
import static com.github.achaaab.lceb.modele.ResultatManche.PERDUE_PLUS_LOIN;
import static com.github.achaaab.lceb.modele.ResultatManche.PERDUE_PLUS_LONGUE;
import static com.github.achaaab.lceb.utilitaire.StringUtilitaire.SEPARATEUR_LIGNES;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Manche {

	private Tirage tirage;
	private Tirage tirageSauvegarde;
	private Solution solutionManuelle;
	private Solution solutionReference;

	/**
	 * @since 0.0.0
	 */
	public Manche() {

	}

	/**
	 * @since 0.0.0
	 */
	public void sauvegarderTirage() {
		tirageSauvegarde = tirage;
	}

	/**
	 * @since 0.0.0
	 */
	public void restaurerTirage() {
		tirage = tirageSauvegarde;
	}

	/**
	 * @param resultatManche
	 * @since 0.0.0
	 */
	public void afficherResultat(ResultatManche resultatManche) {

		var message = "ma solution était : " + SEPARATEUR_LIGNES + SEPARATEUR_LIGNES + solutionReference;

		if (resultatManche != null) {
			message += SEPARATEUR_LIGNES + resultatManche;
		}

		showMessageDialog(null, message, "Résultat", INFORMATION_MESSAGE);
	}

	/**
	 * @param solutionManuelleValidee
	 * @return
	 * @since 0.0.0
	 */
	public ResultatManche getResultat(boolean solutionManuelleValidee) {

		ResultatManche resultat;

		if (solutionManuelleValidee) {

			var compte = tirage.getCompte();

			solutionManuelle.calculerDistance(compte);
			solutionReference.calculerDistance(compte);

			var distanceManuelle = solutionManuelle.getDistance();
			var distanceReference = solutionReference.getDistance();

			if (distanceManuelle < distanceReference) {

				resultat = GAGNEE_PLUS_PROCHE;

			} else if (distanceManuelle > distanceReference) {

				resultat = PERDUE_PLUS_LOIN;

			} else {

				var nombreOperationsManuel = solutionManuelle.getNombreOperations();
				var nombreOperationsReference = solutionReference.getNombreOperations();

				if (nombreOperationsManuel < nombreOperationsReference) {
					resultat = GAGNEE_PLUS_COURTE;
				} else if (nombreOperationsManuel > nombreOperationsReference) {
					resultat = PERDUE_PLUS_LONGUE;
				} else {
					resultat = NULLE;
				}
			}

		} else {

			resultat = PERDUE_ABANDONNEE;
		}

		return resultat;
	}

	/**
	 * @param solutionManuelle solution trouvée par l'utilisateur
	 * @since 0.0.0
	 */
	public void setSolutionManuelle(Solution solutionManuelle) {

		this.solutionManuelle = solutionManuelle;

		solutionReference.calculerDistance(tirage.getCompte());
	}

	/**
	 * @param solutionReference solution trouvée par le solveur,
	 * qui servira de référence pour determiner le resultat de la manche
	 * @since 0.0.0
	 */
	public synchronized void setSolutionReference(Solution solutionReference) {

		this.solutionReference = solutionReference;

		solutionReference.calculerDistance(tirage.getCompte());
	}

	/**
	 * @return tirage de la manche
	 * @since 0.0.0
	 */
	public Tirage getTirage() {
		return tirage;
	}

	/**
	 * @param tirage tirage de la manche
	 * @since 0.0.0
	 */
	public void setTirage(Tirage tirage) {
		this.tirage = tirage;
	}

	/**
	 * @since 0.0.0
	 */
	public void setTirageAleatoire() {

		tirage = new Tirage();
		tirage.genererAleatoirement();
	}
}