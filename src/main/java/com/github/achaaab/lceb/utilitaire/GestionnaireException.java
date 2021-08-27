package com.github.achaaab.lceb.utilitaire;

import javax.swing.JFrame;

import static com.github.achaaab.lceb.utilitaire.ErreurUtilitaire.getErreurInitiale;
import static com.github.achaaab.lceb.utilitaire.StringUtilitaire.SEPARATEUR_LIGNES;
import static com.github.achaaab.lceb.utilitaire.StringUtilitaire.multiligne;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class GestionnaireException {

	private static final int LONGUEUR_MAXIMUM_LIGNE = 60;

	/**
	 * @param erreur
	 * @since 0.0.0
	 */
	public static void traiter(Throwable erreur) {

		var fenetreErreur = new JFrame();

		var typeErreur = erreur.getClass().getSimpleName();
		var messageErreur = erreur.getLocalizedMessage();

		var message = new StringBuilder();

		message.
				append(typeErreur).
				append(SEPARATEUR_LIGNES);

		if (messageErreur != null) {

			message.
					append(SEPARATEUR_LIGNES).
					append(messageErreur);
		}

		var erreurInitiale = getErreurInitiale(erreur);

		if (erreurInitiale != erreur) {

			var typeErreurInitiale = erreurInitiale.getClass().getSimpleName();

			var messageErreurInitiale = erreurInitiale.getLocalizedMessage();

			message.
					append(SEPARATEUR_LIGNES).
					append("Cause : ");

			message.append(typeErreurInitiale);

			if (messageErreurInitiale != null) {

				message.
						append('(').
						append(messageErreurInitiale).
						append(')');
			}
		}

		var messageMultiligne = multiligne(message.toString(), LONGUEUR_MAXIMUM_LIGNE);

		showMessageDialog(fenetreErreur, messageMultiligne, "Erreur", ERROR_MESSAGE);
	}
}