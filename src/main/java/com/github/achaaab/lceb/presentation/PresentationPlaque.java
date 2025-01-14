package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.resolution.Plaque;
import com.github.achaaab.lceb.modele.resolution.manuelle.commande.UtilisationOperande;

import java.awt.Font;

import static com.github.achaaab.lceb.presentation.ViewScale.scaleAndRound;
import static java.awt.Color.BLUE;
import static java.awt.Font.BOLD;

/**
 * Présentation d'un plaque contenant un nombre, utilisable pour la résolution manuelle.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationPlaque extends PresentationCommande {

	private final Plaque plaque;

	/**
	 * @param plaque
	 * @since 0.0.0
	 */
	public PresentationPlaque(Plaque plaque) {

		super(new UtilisationOperande(plaque));

		this.plaque = plaque;

		setForeground(BLUE);
		setFont(new Font("Dialog", BOLD, scaleAndRound(28)));
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		setVisible(!plaque.isUtilisee());
		setText(plaque.toString());
	}
}
