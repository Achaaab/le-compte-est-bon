package com.github.achaaab.lceb.modele.resolution.manuelle.commande;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

/**
 * commande d'abandon du calcul
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record AbandonCalcul(LeCompteEstBon leCompteEstBon) implements Runnable {

	@Override
	public void run() {
		leCompteEstBon.abandonnerCalcul();
	}
}