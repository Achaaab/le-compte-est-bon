package com.github.achaaab.lceb.presentation;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

import com.github.achaaab.lceb.modele.LeCompteEstBon;
import com.github.achaaab.lceb.modele.resolution.manuelle.commande.AbandonCalcul;
import com.github.achaaab.lceb.modele.resolution.manuelle.commande.EffacementArdoise;
import com.github.achaaab.lceb.modele.resolution.manuelle.commande.EffacementElement;
import com.github.achaaab.lceb.modele.resolution.manuelle.commande.ValidationCalcul;

import static com.github.achaaab.lceb.modele.resolution.Operateur.DIVISE;
import static com.github.achaaab.lceb.modele.resolution.Operateur.MOINS;
import static com.github.achaaab.lceb.modele.resolution.Operateur.MULTIPLIE;
import static com.github.achaaab.lceb.modele.resolution.Operateur.PLUS;
import static com.github.achaaab.lceb.utilitaire.FichierUtilitaire.getIcone;
import static com.github.achaaab.lceb.utilitaire.GestionnaireException.traiter;

/**
 * panneau regroupant toutes les commandes disponibles pour l'utilisateur
 * 
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationCommandes extends JPanel {

	/**
	 * @param leCompteEstBon
	 * @since 0.0.0
	 */
	public PresentationCommandes(LeCompteEstBon leCompteEstBon) {

		setLayout(new GridLayout(4, 2, 0, 0));

		var presentationPlus = new PresentationOperateur(PLUS, leCompteEstBon);
		var presentationMoins = new PresentationOperateur(MOINS, leCompteEstBon);
		var presentationMultiplie = new PresentationOperateur(MULTIPLIE, leCompteEstBon);
		var presentationDivise = new PresentationOperateur(DIVISE, leCompteEstBon);

		var ardoise = leCompteEstBon.getArdoise();

		var effacementArdoise = new EffacementArdoise(ardoise);
		var presentationEffacementArdoise = new PresentationCommande(effacementArdoise);
		presentationEffacementArdoise.setToolTipText("Effacer l'ardoise");

		var effacementElement = new EffacementElement(leCompteEstBon);
		var presentationEffacementElement = new PresentationCommande(effacementElement);
		presentationEffacementElement.setToolTipText("Effacer un élément");

		var validationCalcul = new ValidationCalcul(leCompteEstBon);
		var presentationValidationCalcul = new PresentationCommande(validationCalcul);
		presentationValidationCalcul.setToolTipText("Valider");

		var abandonCalcul = new AbandonCalcul(leCompteEstBon);
		var presentationAbandonCalcul = new PresentationCommande(abandonCalcul);
		presentationAbandonCalcul.setToolTipText("Abandonner");

		add(presentationPlus);
		add(presentationEffacementArdoise);
		add(presentationMoins);
		add(presentationEffacementElement);
		add(presentationMultiplie);
		add(presentationValidationCalcul);
		add(presentationDivise);
		add(presentationAbandonCalcul);

		try {

			presentationEffacementArdoise.setIcon(getIcone("images/gomme_48.png"));
			presentationEffacementElement.setIcon(getIcone("images/gomme_24.png"));
			presentationValidationCalcul.setIcon(getIcone("images/validation_48.png"));
			presentationAbandonCalcul.setIcon(getIcone("images/abandon_48.png"));

		} catch (IOException erreur) {

			traiter(erreur);
		}
	}
}
