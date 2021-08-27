package com.github.achaaab.lceb.modele;

import com.github.achaaab.lceb.modele.resolution.OperationInterdite;
import com.github.achaaab.lceb.modele.resolution.Plaque;
import com.github.achaaab.lceb.modele.resolution.Solution;
import com.github.achaaab.lceb.modele.resolution.automatique.Solveur;
import com.github.achaaab.lceb.modele.resolution.manuelle.Ardoise;
import com.github.achaaab.lceb.modele.resolution.manuelle.ElementCalcul;
import com.github.achaaab.lceb.modele.resolution.manuelle.ElementCalculInterdit;
import com.github.achaaab.lceb.presentation.PresentationLeCompteEstBon;

import static com.github.achaaab.lceb.modele.Tirage.NOMBRE_PLAQUES;

/**
 * moteur du jeu centralisant le déroulement des parties
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class LeCompteEstBon {

	private static final int NIVEAU_DEFAUT = 3;

	private final Plaque[] plaques;
	private final Ardoise ardoise;
	private final Partie partie;
	private final PresentationLeCompteEstBon presentation;

	private int compte;
	private int niveauSolveur;

	/**
	 * @since 0.0.0
	 */
	public LeCompteEstBon() {

		niveauSolveur = NIVEAU_DEFAUT;

		plaques = new Plaque[NOMBRE_PLAQUES];

		for (var indexPlaque = 0; indexPlaque < NOMBRE_PLAQUES; indexPlaque++) {
			plaques[indexPlaque] = new Plaque(this);
		}

		ardoise = new Ardoise();
		partie = new Partie(niveauSolveur);

		presentation = new PresentationLeCompteEstBon(this);
	}

	/**
	 * démarre une nouvelle partie
	 *
	 * @since 0.0.0
	 */
	public void nouvellePartie() {

		partie.initialiser();
		nouvelleManche();
	}

	/**
	 * démarre une nouvelle manche
	 *
	 * @since 0.0.0
	 */
	private void nouvelleManche() {

		ardoise.effacer();
		partie.nouvelleManche();
		lireTirage();
		presentation.actualiser();
	}

	/**
	 * @since 0.0.0
	 */
	private void lireTirage() {

		var manche = partie.getManche();
		var tirage = manche.getTirage();

		for (var indexPlaque = 0; indexPlaque < NOMBRE_PLAQUES; indexPlaque++) {
			plaques[indexPlaque].setNombre(tirage.getNombre(indexPlaque));
		}

		compte = tirage.getCompte();
	}

	/**
	 * affiche la fenêtre de saisie d'un tirage
	 *
	 * @since 0.0.0
	 */
	public void afficherTiragePersonnalise() {
		presentation.afficherTirage();
	}

	/**
	 * démarre une manche avec un tirage compliqué
	 *
	 * @since 0.0.0
	 */
	public void jouerTirageComplique() {

		var tirage = new Tirage();
		int compte;
		int nombreOperations;
		int distance;
		Solution solutionOptimale;

		do {

			tirage.genererAleatoirement();
			compte = tirage.getCompte();
			solutionOptimale = new Solveur().getSolution(tirage);
			nombreOperations = solutionOptimale.getNombreOperations();
			distance = solutionOptimale.calculerDistance(compte);

		} while (distance > 0 || nombreOperations < 5);

		jouerTiragePersonnalise(tirage);
	}

	/**
	 * démarre une manche personnalisée
	 *
	 * @since 0.0.0
	 */
	public void jouerTiragePersonnalise(Tirage tiragePersonnalise) {

		ardoise.effacer();
		partie.manchePersonnalisee(tiragePersonnalise);
		lireTirage();
		presentation.actualiser();
	}

	/**
	 * ajoute un élément de calcul sur l'ardoise (opérande ou opérateur)
	 *
	 * @param elementCalcul
	 * @throws ElementCalculInterdit
	 * @throws OperationInterdite
	 * @since 0.0.0
	 */
	public void utiliserElementCalcul(ElementCalcul elementCalcul) throws ElementCalculInterdit, OperationInterdite {
		ardoise.ajouterElementCalcul(elementCalcul);
	}

	/**
	 * efface le dernier élément de calcul ajouté sur l'ardoise
	 *
	 * @since 0.0.0
	 */
	public void effacerElementCalcul() {
		ardoise.effacerElementCalcul();
	}

	/**
	 * valide la solution manuelle
	 *
	 * @since 0.0.0
	 */
	public void validerSolutionManuelle() {

		var solutionManuelle = ardoise.getSolution();
		partie.setSolutionManuelle(solutionManuelle);
		nouvelleManche();
	}

	/**
	 * on abandonne le calcul
	 *
	 * @since 0.0.0
	 */
	public void abandonnerCalcul() {

		partie.abandonnerManche();
		nouvelleManche();
	}

	/**
	 * valorise le niveau du solveur
	 *
	 * @param niveauSolveur nouveau niveau du solveur (entre 1 et 5)
	 * @since 0.0.0
	 */
	public void setNiveauSolveur(int niveauSolveur) {

		if (this.niveauSolveur != niveauSolveur) {

			this.niveauSolveur = niveauSolveur;
			partie.setNiveauSolveur(niveauSolveur);

			if (partie.isCommencee()) {
				nouvellePartie();
			}
		}
	}

	/**
	 * @return plaques utilisables
	 * @since 0.0.0
	 */
	public Plaque[] getPlaques() {
		return plaques;
	}

	/**
	 * @return partie en cours
	 * @since 0.0.0
	 */
	public Partie getPartie() {
		return partie;
	}

	/**
	 * @return compte à obtenir
	 * @since 0.0.0
	 */
	public int getCompte() {
		return compte;
	}

	/**
	 * @return ardoise pour faire le calcul manuellement
	 * @since 0.0.0
	 */
	public Ardoise getArdoise() {
		return ardoise;
	}

	/**
	 * @return niveau du solveur
	 * @since 0.0.0
	 */
	public int getNiveauSolveur() {
		return niveauSolveur;
	}

	/**
	 * @return présentation du jeu
	 * @since 0.0.0
	 */
	public PresentationLeCompteEstBon getPresentation() {
		return presentation;
	}
}