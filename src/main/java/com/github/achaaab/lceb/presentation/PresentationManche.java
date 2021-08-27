package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.Partie;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;

import static com.github.achaaab.lceb.utilitaire.SwingUtilitaire.scale;
import static java.awt.Color.BLACK;
import static java.awt.Font.BOLD;

/**
 * présentation de la manche en cours
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationManche extends JLabel {

	private final Partie partie;

	/**
	 * @param partie
	 * @since 0.0.0
	 */
	public PresentationManche(Partie partie) {

		this.partie = partie;

		setForeground(BLACK);
		setFont(new Font("Dialog", BOLD, 14));
		setHorizontalAlignment(LEFT);
		setVerticalAlignment(CENTER);
		setPreferredSize(new Dimension(128, 32));
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		var manche = partie.getCompteurManche();
		var chaineManche = "Manche : " + manche;
		setText(chaineManche);
	}
}