package com.github.achaaab.lceb.modele.resolution.manuelle.commande;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

/**
 * commande d'effacement d'un élément de calcul sur l'ardoise
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record EffacementElement(LeCompteEstBon leCompteEstBon) implements Runnable {

	@Override
	public void run() {
		leCompteEstBon.effacerElementCalcul();
	}
}