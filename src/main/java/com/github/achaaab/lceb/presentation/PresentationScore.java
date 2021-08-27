package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.Partie;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import static com.github.achaaab.lceb.utilitaire.SwingUtilitaire.scale;
import static java.awt.Font.BOLD;

/**
 * présentation du score de la partie
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationScore extends JLabel {

	private static final Color COULEUR_EGALITE = new Color(240, 128, 0);
	private static final Color COULEUR_VICTOIRE = new Color(0, 192, 0);
	private static final Color COULEUR_DEFAITE = new Color(240, 0, 0);

	private final Partie partie;

	/**
	 * @param partie
	 * @øince 0.0.0
	 */
	public PresentationScore(Partie partie) {

		this.partie = partie;

		setFont(new Font("Dialog", BOLD, 14));
		setHorizontalAlignment(LEFT);
		setVerticalAlignment(CENTER);
		setPreferredSize(new Dimension(128, 32));
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		var scoreJoueur = partie.getScoreJoueur();
		var scoreOrdinateur = partie.getScoreOrdinateur();

		setText("Score : " + scoreJoueur + " / " + scoreOrdinateur);

		if (scoreJoueur < scoreOrdinateur) {
			setForeground(COULEUR_DEFAITE);
		} else if (scoreJoueur > scoreOrdinateur) {
			setForeground(COULEUR_VICTOIRE);
		} else {
			setForeground(COULEUR_EGALITE);
		}
	}
}