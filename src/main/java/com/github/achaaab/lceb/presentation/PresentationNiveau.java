package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

import javax.swing.JRadioButtonMenuItem;

/**
 * présentation d'un niveau de solveur disponible
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationNiveau extends JRadioButtonMenuItem {

	private final LeCompteEstBon leCompteEstBon;
	private final int niveau;

	/**
	 * @param leCompteEstBon
	 * @param niveau
	 * @since 0.0.0
	 */
	public PresentationNiveau(LeCompteEstBon leCompteEstBon, int niveau) {

		super("Niveau " + niveau);

		this.leCompteEstBon = leCompteEstBon;
		this.niveau = niveau;

		addActionListener(evenement -> changerNiveau());
	}

	/**
	 * @since 0.0.0
	 */
	private void changerNiveau() {
		leCompteEstBon.setNiveauSolveur(niveau);
	}
}
