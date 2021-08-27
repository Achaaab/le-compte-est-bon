package com.github.achaaab.lceb.modele.resolution.manuelle;

import com.github.achaaab.lceb.modele.resolution.Plaque;

/**
 * Une opérande est une plaque couplée à un nombre (en effet, il n'y a que 6 plaques en jeu
 * mais elles peuvent changer de valeur au cours du calcul puisqu'on replace le résultat de chaque operation
 * dans une des 6 plaques existantes).
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Operande implements ElementCalcul {

	private final Plaque plaque;
	private final int nombre;

	/**
	 * @param plaque
	 * @since 0.0.0
	 */
	public Operande(Plaque plaque) {

		this.plaque = plaque;

		nombre = plaque.getNombre();
	}

	/**
	 * @return plaque
	 * @since 0.0.0
	 */
	public Plaque getPlaque() {
		return plaque;
	}

	/**
	 * @return nombre
	 * @since 0.0.0
	 */
	public int getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return Integer.toString(nombre);
	}
}