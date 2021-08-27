package com.github.achaaab.lceb.modele.resolution.manuelle.commande;

import com.github.achaaab.lceb.modele.resolution.manuelle.Ardoise;

/**
 * commande d'effacement de l'ardoise entière
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public record EffacementArdoise(Ardoise ardoise) implements Runnable {

	@Override
	public void run() {
		ardoise.effacer();
	}
}