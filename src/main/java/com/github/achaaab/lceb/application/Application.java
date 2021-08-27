package com.github.achaaab.lceb.application;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import static com.github.achaaab.lceb.utilitaire.FichierUtilitaire.getImage;
import static com.github.achaaab.lceb.utilitaire.GestionnaireException.traiter;
import static com.github.achaaab.lceb.utilitaire.LookAndFeelUtilitaire.setLookAndFeelParNom;
import static com.github.achaaab.lceb.utilitaire.SwingUtilitaire.scale;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Application {

	/**
	 * @param arguments aucun
	 * @since 0.0.0
	 */
	public static void main(String... arguments) {

		try {

			setLookAndFeelParNom("Nimbus");

			var leCompteEstBon = new LeCompteEstBon();
			var presentation = leCompteEstBon.getPresentation();
			var icone = getImage("images/calculatrice_128.png");
			var fenetre = new JFrame("Le compte est bon");

			fenetre.setContentPane(presentation);
			fenetre.setIconImage(icone);
			fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			scale(fenetre);
			fenetre.pack();
			fenetre.setLocationRelativeTo(null);
			fenetre.setVisible(true);

			leCompteEstBon.nouvellePartie();

		} catch (Exception exception) {

			traiter(exception);
		}
	}
}