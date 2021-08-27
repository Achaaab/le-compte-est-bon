package com.github.achaaab.lceb.presentation;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.github.achaaab.lceb.modele.resolution.Plaque;

/**
 * panneau regroupant toutes les plaques disponibles
 * 
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class PresentationPlaques extends JPanel {

	private final List<PresentationPlaque> presentationsPlaques;

	/**
	 * @param plaques
	 * @since 0.0.0
	 */
	public PresentationPlaques(Plaque[] plaques) {

		super(new GridLayout(1, plaques.length));

		presentationsPlaques = new ArrayList<>(plaques.length);

		for (var plaque : plaques) {

			var presentationPlaque = plaque.getPresentation();
			presentationsPlaques.add(presentationPlaque);
			add(presentationPlaque);
		}
	}

	/**
	 * @since 0.0.0
	 */
	public void actualiser() {
		presentationsPlaques.forEach(PresentationPlaque::actualiser);
	}
}