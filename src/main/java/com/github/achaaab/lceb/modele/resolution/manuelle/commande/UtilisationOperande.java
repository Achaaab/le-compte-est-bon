package com.github.achaaab.lceb.modele.resolution.manuelle.commande;

import com.github.achaaab.lceb.modele.resolution.OperationInterdite;
import com.github.achaaab.lceb.modele.resolution.Plaque;
import com.github.achaaab.lceb.modele.resolution.manuelle.ElementCalculInterdit;

import static com.github.achaaab.lceb.utilitaire.GestionnaireException.traiter;

/**
 * utilisation d'une opérande sur l'ardoise
 * 
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class UtilisationOperande implements Runnable {

	private final Plaque plaque;

	/**
	 * @param plaque
	 * @since 0.0.0
	 */
	public UtilisationOperande(Plaque plaque) {
		this.plaque = plaque;
	}

	@Override
	public void run() {

		// si la plaque est inutilisable on affiche une exception expliquant pourquoi

		try {
			plaque.utiliser();
		} catch (ElementCalculInterdit | OperationInterdite erreur) {
			traiter(erreur);
		}
	}
}