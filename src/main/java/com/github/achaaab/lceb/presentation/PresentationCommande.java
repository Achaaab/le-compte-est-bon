package com.github.achaaab.lceb.presentation;

import javax.swing.JButton;
import java.awt.Dimension;

import static com.github.achaaab.lceb.presentation.ViewScale.scale;

/**
 * présentation d'une commande sous forme de bouton
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationCommande extends JButton {

	/**
	 * @param commande
	 * @since 0.0.0
	 */
	public PresentationCommande(Runnable commande) {

		setPreferredSize(scale(new Dimension(64, 64)));
		addActionListener(evenement -> commande.run());
	}
}
