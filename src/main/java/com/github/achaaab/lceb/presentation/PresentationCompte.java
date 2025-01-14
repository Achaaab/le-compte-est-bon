package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;

import static com.github.achaaab.lceb.presentation.ViewScale.scale;
import static com.github.achaaab.lceb.presentation.ViewScale.scaleAndRound;
import static java.awt.Color.BLUE;
import static java.awt.Font.BOLD;

/**
 * presentation du compte à obtenir
 * 
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationCompte extends JLabel {

	private final LeCompteEstBon leCompteEstBon;

	/**
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public PresentationCompte(LeCompteEstBon leCompteEstBon) {

		this.leCompteEstBon = leCompteEstBon;

		setForeground(BLUE);
		setFont(new Font("Dialog", BOLD, scaleAndRound(48)));
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setPreferredSize(scale(new Dimension(128, 64)));
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		var compte = leCompteEstBon.getCompte();
		var chaineCompte = Integer.toString(compte);
		setText(chaineCompte);
	}
}