package com.github.achaaab.lceb.modele.resolution.manuelle.commande;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

/**
 * commande de validation de la solution manuelle
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record ValidationCalcul(LeCompteEstBon leCompteEstBon) implements Runnable {

	@Override
	public void run() {
		leCompteEstBon.validerSolutionManuelle();
	}
}