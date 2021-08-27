package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.Partie;

import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationPartie extends JPanel {

	private final PresentationScore presentationScore;
	private final PresentationManche presentationManche;

	/**
	 * @param partie
	 * @since 0.0.0
	 */
	public PresentationPartie(Partie partie) {

		super(new GridLayout(2, 1));

		presentationScore = new PresentationScore(partie);
		presentationManche = new PresentationManche(partie);

		add(presentationScore);
		add(presentationManche);
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		presentationScore.actualiser();
		presentationManche.actualiser();
	}
}