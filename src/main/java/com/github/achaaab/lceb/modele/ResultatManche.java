package com.github.achaaab.lceb.modele;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public enum ResultatManche {

	GAGNEE_PLUS_PROCHE("Bravo, vous avez gagné cette manche !"),
	GAGNEE_PLUS_COURTE("Bravo, vous avez gagné cette manche !\nVous avez trouvé une solution plus courte."),
	NULLE("Cette manche est nulle."),
	PERDUE_PLUS_LONGUE("Vous avez perdu cette manche.\nJ'ai trouvé une solution plus courte."),
	PERDUE_PLUS_LOIN("Vous avez perdu cette manche."),
	PERDUE_ABANDONNEE("Vous avez perdu cette manche.");

	private final String string;

	/**
	 * @param string
	 * @since 0.0.0
	 */
	ResultatManche(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}