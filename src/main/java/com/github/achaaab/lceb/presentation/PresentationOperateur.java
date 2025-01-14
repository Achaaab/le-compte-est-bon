package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.LeCompteEstBon;
import com.github.achaaab.lceb.modele.resolution.Operateur;
import com.github.achaaab.lceb.modele.resolution.manuelle.commande.UtilisationOperateur;

import java.awt.Font;

import static com.github.achaaab.lceb.presentation.ViewScale.scaleAndRound;
import static java.awt.Color.BLACK;
import static java.awt.Font.BOLD;

/**
 * présentation d'un opérateur
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationOperateur extends PresentationCommande {

	/**
	 * @param operateur
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public PresentationOperateur(Operateur operateur, LeCompteEstBon leCompteEstBon) {

		super(new UtilisationOperateur(operateur, leCompteEstBon));

		setForeground(BLACK);
		setFont(new Font("Dialog", BOLD, scaleAndRound(32)));
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);

		setText(operateur.toString());
	}
}