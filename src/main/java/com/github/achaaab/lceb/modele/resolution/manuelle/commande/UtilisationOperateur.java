package com.github.achaaab.lceb.modele.resolution.manuelle.commande;

import com.github.achaaab.lceb.modele.LeCompteEstBon;
import com.github.achaaab.lceb.modele.resolution.Operateur;
import com.github.achaaab.lceb.modele.resolution.OperationInterdite;
import com.github.achaaab.lceb.modele.resolution.manuelle.ElementCalculInterdit;
import com.github.achaaab.lceb.utilitaire.GestionnaireException;

import static com.github.achaaab.lceb.utilitaire.GestionnaireException.traiter;

/**
 * utilisation d'un opérateur sur l'ardoise
 * 
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class UtilisationOperateur implements Runnable {

	private final Operateur operateur;
	private final LeCompteEstBon leCompteEstBon;

	/**
	 * @param operateur
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public UtilisationOperateur(Operateur operateur, LeCompteEstBon leCompteEstBon) {

		this.operateur = operateur;
		this.leCompteEstBon = leCompteEstBon;
	}

	@Override
	public void run() {

		// si l'opérateur est inutilisable, on affiche une erreur expliquant pourquoi

		try {
			leCompteEstBon.utiliserElementCalcul(operateur);
		} catch (ElementCalculInterdit | OperationInterdite erreur) {
			traiter(erreur);
		}
	}
}