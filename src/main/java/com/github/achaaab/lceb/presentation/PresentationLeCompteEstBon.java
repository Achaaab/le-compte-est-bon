package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;

/**
 * panneau principal de l'application, il regroupe tous les autres composants
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationLeCompteEstBon extends JPanel {

	private final PresentationPlaques presentationPlaques;
	private final PresentationCompte presentationCompte;
	private final PresentationPartie presentationPartie;
	private final PresentationTirage presentationTirage;

	/**
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public PresentationLeCompteEstBon(LeCompteEstBon leCompteEstBon) {

		super(new BorderLayout());

		var plaques = leCompteEstBon.getPlaques();
		var ardoise = leCompteEstBon.getArdoise();
		var partie = leCompteEstBon.getPartie();

		presentationPlaques = new PresentationPlaques(plaques);
		presentationCompte = new PresentationCompte(leCompteEstBon);
		presentationPartie = new PresentationPartie(partie);
		presentationTirage = new PresentationTirage(leCompteEstBon);

		var presentationArdoise = ardoise.getPresentation();
		var presentationCommandes = new PresentationCommandes(leCompteEstBon);
		var presentationMenu = new PresentationMenu(leCompteEstBon);

		var panneauNord = new JPanel(new BorderLayout());
		panneauNord.add(presentationCompte, CENTER);
		panneauNord.add(presentationPartie, EAST);

		var panneauCentre = new JPanel(new BorderLayout());
		panneauCentre.add(presentationPlaques, NORTH);
		panneauCentre.add(presentationArdoise, CENTER);
		panneauCentre.add(presentationCommandes, EAST);

		var panneauPrincipal = new JPanel(new BorderLayout());
		panneauPrincipal.add(panneauNord, NORTH);
		panneauPrincipal.add(panneauCentre, CENTER);

		add(presentationMenu, NORTH);
		add(panneauPrincipal, CENTER);
	}

	/**
	 * @since 0.0.0
	 */
	public void afficherTirage() {
		presentationTirage.afficher();
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {

		presentationCompte.actualiser();
		presentationPartie.actualiser();
		presentationPlaques.actualiser();
	}
}