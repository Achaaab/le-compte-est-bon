package com.github.achaaab.lceb.utilitaire;

import static java.lang.Math.max;
import static java.lang.System.getProperty;
import static java.util.stream.Collectors.toList;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class StringUtilitaire {

	public static final String SEPARATEUR_LIGNES = getProperty("line.separator");
	public static final String CHAINE_VIDE = "";

	/**
	 * Permet de découper une chaîne de caractères en insérant des séparateur de ligne
	 * afin que chaque ligne ne dépasse pas un nombre donné de caractères.
	 *
	 * @param chaine
	 * @param longueurLigne
	 * @return
	 * @since 0.0.0
	 */
	public static String multiligne(String chaine, int longueurLigne) {

		var multiligne = new StringBuilder();
		var lignes = chaine.lines().collect(toList());

		int indexDernierBlanc;
		String ligneCoupee;

		for (var ligne : lignes) {

			while (ligne.length() > longueurLigne) {

				ligneCoupee = ligne.substring(0, longueurLigne + 1);

				indexDernierBlanc = max(
						ligneCoupee.lastIndexOf(' '),
						ligneCoupee.lastIndexOf('\t'));

				if (indexDernierBlanc < 0) {

					multiligne.
							append(ligne, 0, longueurLigne).
							append(SEPARATEUR_LIGNES);

					ligne = ligne.substring(longueurLigne);

				} else {

					multiligne.
							append(ligne, 0, indexDernierBlanc).
							append(SEPARATEUR_LIGNES);

					ligne = ligne.substring(indexDernierBlanc + 1);
				}
			}

			multiligne.
					append(ligne).
					append(SEPARATEUR_LIGNES);
		}

		return multiligne.toString();
	}
}