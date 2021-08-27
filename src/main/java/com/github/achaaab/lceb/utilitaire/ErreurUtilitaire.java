package com.github.achaaab.lceb.utilitaire;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ErreurUtilitaire {

	/**
	 * @param erreur
	 * @return
	 * @since 0.0.0
	 */
	public static Throwable getErreurInitiale(Throwable erreur) {

		var cause = erreur;

		while (erreur.getCause() != null) {
			cause = erreur.getCause();
		}

		return cause;
	}
}