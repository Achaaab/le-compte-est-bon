package com.github.achaaab.lceb.presentation;

import com.github.achaaab.lceb.modele.LeCompteEstBon;
import com.github.achaaab.lceb.modele.Tirage;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.achaaab.lceb.modele.Tirage.NOMBRES_POSSIBLES_UNIQUES;
import static com.github.achaaab.lceb.modele.Tirage.NOMBRE_PLAQUES;
import static com.github.achaaab.lceb.utilitaire.FichierUtilitaire.getImage;
import static com.github.achaaab.lceb.utilitaire.GestionnaireException.traiter;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Font.BOLD;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationTirage extends JFrame {

	private final List<JComboBox<Integer>> presentationsPlaques;
	private final JTextField presentationCompte;
	private final LeCompteEstBon leCompteEstBon;

	/**
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public PresentationTirage(LeCompteEstBon leCompteEstBon) {

		super("Tirage personnalisé");

		this.leCompteEstBon = leCompteEstBon;

		try {

			var icone = getImage("images/calculatrice_128.png");
			setIconImage(icone);

		} catch (IOException erreur) {

			traiter(erreur);
		}

		var panneauNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var panneauCentre = new JPanel(new GridLayout(1, NOMBRE_PLAQUES));
		var panneauSud = new JPanel(new FlowLayout(FlowLayout.CENTER));

		presentationCompte = new JTextField("999", 3);
		presentationCompte.setFont(new Font("Dialog", BOLD, 14));
		panneauNord.add(presentationCompte);

		presentationsPlaques = new ArrayList<>();

		for (var indexPlaque = 0; indexPlaque < NOMBRE_PLAQUES; indexPlaque++) {

			var presentationPlaque = new JComboBox<>(NOMBRES_POSSIBLES_UNIQUES);
			presentationPlaque.setFont(new Font("Dialog", BOLD, 14));
			presentationsPlaques.add(presentationPlaque);
			panneauCentre.add(presentationPlaque);
		}

		var valider = new JButton("Valider");
		var annuler = new JButton("Annuler");

		panneauSud.add(valider);
		panneauSud.add(annuler);

		add(panneauNord, NORTH);
		add(panneauCentre, BorderLayout.CENTER);
		add(panneauSud, SOUTH);

		pack();

		setResizable(false);

		valider.addActionListener(evenement -> valider());
		annuler.addActionListener(evenement -> annuler());
	}

	/**
	 * @since 0.0.0
	 */
	public void afficher() {

		setLocationRelativeTo(leCompteEstBon.getPresentation());
		setVisible(true);
	}

	/**
	 * @since 0.0.0
	 */
	public void valider() {

		var tirage = new Tirage();

		for (var indexPlaque = 0; indexPlaque < NOMBRE_PLAQUES; indexPlaque++) {

			var presentationPlaque = presentationsPlaques.get(indexPlaque);
			var nombre = (Integer) presentationPlaque.getSelectedItem();
			tirage.setNombre(indexPlaque, nombre == null ? 0 : nombre);
		}

		try {

			int compte = Integer.parseInt(presentationCompte.getText());
			tirage.setCompte(compte);
			setVisible(false);
			leCompteEstBon.jouerTiragePersonnalise(tirage);

		} catch (NumberFormatException erreurFormat) {

			traiter(erreurFormat);
		}
	}

	/**
	 * @since 0.0.0
	 */
	public void annuler() {
		setVisible(false);
	}
}