package com.github.achaaab.lceb.modele.resolution;

import com.github.achaaab.lceb.modele.LeCompteEstBon;
import com.github.achaaab.lceb.modele.resolution.manuelle.ElementCalculInterdit;
import com.github.achaaab.lceb.modele.resolution.manuelle.Operande;
import com.github.achaaab.lceb.presentation.PresentationPlaque;

import static com.github.achaaab.lceb.utilitaire.StringUtilitaire.CHAINE_VIDE;

/**
 * Une plaque contient un nombre et peut être utilisée comme élément de calcul pour la résolution manuelle.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Plaque {

	private final LeCompteEstBon leCompteEstBon;
	private final PresentationPlaque presentation;

	private int nombre;
	private boolean utilisee;

	/**
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public Plaque(LeCompteEstBon leCompteEstBon) {

		this.leCompteEstBon = leCompteEstBon;

		nombre = 0;
		utilisee = false;

		presentation = new PresentationPlaque(this);
	}

	/**
	 * @return nombre
	 * @since 0.0.0
	 */
	public int getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 * @since 0.0.0
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param nombre nouveau nombre
	 * @since 0.0.0
	 */
	public void reinitialiser(int nombre) {

		this.nombre = nombre;

		setUtilisee(false);
	}

	/**
	 * @throws OperationInterdite
	 * @throws ElementCalculInterdit
	 * @since 0.0.0
	 */
	public void utiliser() throws ElementCalculInterdit, OperationInterdite {

		var operande = new Operande(this);
		leCompteEstBon.utiliserElementCalcul(operande);

		setUtilisee(true);
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public boolean isUtilisee() {
		return utilisee;
	}

	/**
	 * @param utilisee
	 * @since 0.0.0
	 */
	public void setUtilisee(boolean utilisee) {

		this.utilisee = utilisee;

		presentation.actualiser();
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public PresentationPlaque getPresentation() {
		return presentation;
	}

	@Override
	public String toString() {

		return utilisee ?
				CHAINE_VIDE :
				Integer.toString(nombre);
	}
}