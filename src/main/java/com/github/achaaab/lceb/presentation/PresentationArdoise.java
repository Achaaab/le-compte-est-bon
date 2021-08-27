package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.resolution.manuelle.Ardoise;
import com.github.achaaab.lceb.utilitaire.SwingUtilitaire;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import static com.github.achaaab.lceb.utilitaire.StringUtilitaire.SEPARATEUR_LIGNES;
import static com.github.achaaab.lceb.utilitaire.SwingUtilitaire.scale;
import static java.awt.Color.WHITE;
import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationArdoise extends JTextArea {

	private final Ardoise modele;

	/**
	 * @param modele
	 * @since 0.0.0
	 */
	public PresentationArdoise(Ardoise modele) {

		this.modele = modele;

		setEditable(false);
		setBackground(new Color(0, 48, 48));
		setForeground(WHITE);
		setFont(new Font("Comic Sans Ms", BOLD | ITALIC, 26));
		setPreferredSize(new Dimension(256, 256));
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		// on calcule le texte à afficher sur l'ardoise
		var texte = new StringBuilder();

		// on récupère les opérations déjà validées

		var solution = modele.getSolution();
		var operations = solution.getOperations();

		for (var operation : operations) {

			texte.
					append(operation).
					append(SEPARATEUR_LIGNES);
		}

		// on récupère les éléments de l'opération en cours

		var etapeCalcul = modele.getNombreElementsCalcul();

		if (etapeCalcul % 3 > 0) {

			texte.append(modele.getOperandeGauche());

			if (etapeCalcul % 3 > 1) {
				texte.append(modele.getOperateur());
			}
		}

		setText(texte.toString());
	}
}