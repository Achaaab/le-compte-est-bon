package com.github.achaaab.lceb.utilitaire;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import static java.awt.Window.getWindows;
import static java.util.Arrays.stream;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class LookAndFeelUtilitaire {

	/**
	 * Tente d'appliquer un look and feel en le recherchant par son nom dans la liste des look and feel installés.
	 * Actualise toutes les fenêtres de l'application.
	 * Ne fait rien si aucun des look and feel installés ne porte le nom fourni.
	 *
	 * @param nom nom du look and feel à appliquer
	 * @throws RuntimeException si le look and feel n'a pas pu être appliqué, quelle qu'en soit la raison
	 * @since 0.0.0
	 */
	public static void setLookAndFeelParNom(String nom) {

		var themes = getInstalledLookAndFeels();
		var themeTrouve = stream(themes).
				filter(theme -> theme.getName().equals(nom)).
				findFirst();

		themeTrouve.
				map(LookAndFeelInfo::getClassName).
				ifPresent(LookAndFeelUtilitaire::setLookAndFeelParClasse);
	}

	/**
	 * Applique un look and feel et actualise les fenêtres existantes.
	 *
	 * @param classe classe du look and feel à appliquer
	 * @throws RuntimeException si le look and feel n'a pas pu être appliqué, quelle qu'en soit la raison
	 * @since 0.0.0
	 */
	public static void setLookAndFeelParClasse(String classe) {

		try {

			setLookAndFeel(classe);

			var fenetres = getWindows();

			for (var fenetre : fenetres) {

				updateComponentTreeUI(fenetre);
				fenetre.pack();
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
				UnsupportedLookAndFeelException cause) {

			throw new RuntimeException(cause);
		}
	}
}