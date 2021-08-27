package com.github.achaaab.lceb.modele;

import com.github.achaaab.lceb.modele.resolution.Solution;
import com.github.achaaab.lceb.modele.resolution.automatique.Ia;
import com.github.achaaab.lceb.presentation.PresentationPartie;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Partie {

	private final Manche manche;
	private final Ia ia;

	private int compteurManche;

	private int scoreJoueur;
	private int scoreOrdinateur;

	private PresentationPartie presentation;
	private boolean manchePersonnalisee;

	/**
	 * @param niveauSolveur
	 * @since 0.0.0
	 */
	public Partie(int niveauSolveur) {

		ia = new Ia();
		ia.setNiveau(niveauSolveur);

		manche = new Manche();

		initialiser();
	}

	/**
	 * Initialise la partie.
	 *
	 * @since 0.0.0
	 */
	public void initialiser() {

		compteurManche = 0;

		scoreJoueur = 0;
		scoreOrdinateur = 0;
	}

	/**
	 * Lance une nouvelle manche.
	 *
	 * @since 0.0.0
	 */
	public final void nouvelleManche() {

		if (manchePersonnalisee) {

			manchePersonnalisee = false;
			manche.restaurerTirage();

		} else {

			compteurManche++;
			manche.setTirageAleatoire();
		}

		calculerSolutionReference();
	}

	/**
	 * @param tiragePersonnalise
	 * @since 0.0.0
	 */
	public void manchePersonnalisee(Tirage tiragePersonnalise) {

		manche.sauvegarderTirage();
		manchePersonnalisee = true;
		manche.setTirage(tiragePersonnalise);

		calculerSolutionReference();
	}

	/**
	 * @param solutionManuelleValidee
	 * @since 0.0.0
	 */
	private void terminerManche(boolean solutionManuelleValidee) {

		var resultatManche = manche.getResultat(solutionManuelleValidee);

		if (manchePersonnalisee) {

			manche.afficherResultat(null);

		} else {

			switch (resultatManche) {

				case GAGNEE_PLUS_PROCHE, GAGNEE_PLUS_COURTE -> scoreJoueur++;
				case PERDUE_PLUS_LONGUE, PERDUE_PLUS_LOIN, PERDUE_ABANDONNEE -> scoreOrdinateur++;
				case NULLE -> {
					scoreJoueur++;
					scoreOrdinateur++;
				}
			}

			manche.afficherResultat(resultatManche);
		}
	}

	/**
	 * @return manche en cours
	 * @since 0.0.0
	 */
	public Manche getManche() {
		return manche;
	}

	/**
	 * @return {@code true} si la manche est commencée, {@code false} sinon
	 * @since 0.0.0
	 */
	public boolean isCommencee() {
		return !manchePersonnalisee && compteurManche > 1;
	}

	/**
	 * @since 0.0.0
	 */
	private void calculerSolutionReference() {

		var tirage = manche.getTirage();
		var solutionReference = ia.getSolution(tirage);
		manche.setSolutionReference(solutionReference);
	}

	/**
	 * @param niveauSolveur niveau du solveur
	 * @since 0.0.0
	 */
	public void setNiveauSolveur(int niveauSolveur) {

		ia.setNiveau(niveauSolveur);
		calculerSolutionReference();
	}

	/**
	 * @param solutionManuelle
	 * @since 0.0.0
	 */
	public void setSolutionManuelle(Solution solutionManuelle) {

		manche.setSolutionManuelle(solutionManuelle);
		terminerManche(true);
	}

	/**
	 * @since 0.0.0
	 */
	public void abandonnerManche() {
		terminerManche(false);
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public int getCompteurManche() {
		return compteurManche;
	}

	/**
	 * @return score du joueur
	 * @since 0.0.0
	 */
	public int getScoreJoueur() {
		return scoreJoueur;
	}

	/**
	 * @return score de l'ordinateur
	 * @since 0.0.0
	 */
	public int getScoreOrdinateur() {
		return scoreOrdinateur;
	}

	/**
	 * @return présentation de la partie
	 * @since 0.0.0
	 */
	public PresentationPartie getPresentation() {
		return presentation;
	}
}