package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.LeCompteEstBon;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import static com.github.achaaab.lceb.presentation.ViewScale.scaleFont;
import static java.lang.System.exit;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * menu de l'application
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationMenu extends JMenuBar {

	private static final float FONT_SIZE = 12;

	private final LeCompteEstBon leCompteEstBon;

	/**
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public PresentationMenu(LeCompteEstBon leCompteEstBon) {

		this.leCompteEstBon = leCompteEstBon;

		var menuFichier = new JMenu("Fichier");
		var menuSolveur = new JMenu("Solveur");
		var menuAide = new JMenu("Aide");

		var nouvellePartie = new JMenuItem("Nouvelle partie");
		var tiragePersonnalise = new JMenuItem("Tirage personnalisé");
		var tirageComplique = new JMenuItem("Tirage compliqué");
		var quitter = new JMenuItem("Quitter");
		var aPropos = new JMenuItem("À propos");

		scaleFont(menuFichier, FONT_SIZE);
		scaleFont(menuSolveur, FONT_SIZE);
		scaleFont(menuAide, FONT_SIZE);
		scaleFont(nouvellePartie, FONT_SIZE);
		scaleFont(tiragePersonnalise, FONT_SIZE);
		scaleFont(tirageComplique, FONT_SIZE);
		scaleFont(quitter, FONT_SIZE);
		scaleFont(aPropos, FONT_SIZE);

		menuFichier.add(nouvellePartie);
		menuFichier.addSeparator();
		menuFichier.add(tiragePersonnalise);
		menuFichier.add(tirageComplique);
		menuFichier.addSeparator();
		menuFichier.add(quitter);
		menuAide.add(aPropos);

		var niveaux = new ButtonGroup();

		PresentationNiveau presentationNiveau;

		for (var niveau = 1; niveau <= 5; niveau++) {

			presentationNiveau = new PresentationNiveau(leCompteEstBon, niveau);
			scaleFont(presentationNiveau, FONT_SIZE);
			niveaux.add(presentationNiveau);
			menuSolveur.add(presentationNiveau);

			if (niveau == leCompteEstBon.getNiveauSolveur()) {
				presentationNiveau.setSelected(true);
			}
		}

		add(menuFichier);
		add(menuSolveur);
		add(menuAide);

		nouvellePartie.addActionListener(evenement -> nouvellePartie());
		tiragePersonnalise.addActionListener(evenement -> tiragePersonnalise());
		tirageComplique.addActionListener(evenement -> tirageComplique());
		quitter.addActionListener(evenement -> quitter());
		aPropos.addActionListener(evenement -> afficherInformations());
	}

	/**
	 * @since 0.0.0
	 */
	private void nouvellePartie() {
		leCompteEstBon.nouvellePartie();
	}

	/**
	 * @since 0.0.0
	 */
	private void tiragePersonnalise() {
		leCompteEstBon.afficherTiragePersonnalise();
	}

	/**
	 * @since 0.0.0
	 */
	private void tirageComplique() {
		leCompteEstBon.jouerTirageComplique();
	}

	/**
	 * @since 0.0.0
	 */
	private void quitter() {
		exit(0);
	}

	/**
	 * @since 0.0.0
	 */
	private void afficherInformations() {

		var informations = """
				Le compte est bon
								
				Concept original : Armand Jammot, 2ème chaîne de l'ORTF
				Version : 0.0.0
				Développeur : Jonathan Guéhenneux
								
				Les icônes proviennent du site
				http://www.iconearchive.com""";

		showMessageDialog(null, informations);
	}
}
