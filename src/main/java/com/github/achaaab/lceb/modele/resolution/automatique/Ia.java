package com.github.achaaab.lceb.modele.resolution.automatique;

import com.github.achaaab.lceb.modele.Tirage;
import com.github.achaaab.lceb.modele.resolution.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.min;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Ia {

	private final Solveur solveur;

	private int niveau;
	private Tirage tirage;
	private Solution solutionIntermediaire;
	private Solution solutionReference;
	private List<Integer> nombresDisponibles;
	private LinkedList<Integer> nombresChoisis;
	private List<Integer> nombresChoisisReference;
	private int nombreNombres;
	private int compte;

	/**
	 * @since 0.0.0
	 */
	public Ia() {

		solveur = new Solveur();
		niveau = 6;
	}

	/**
	 * @param niveau
	 * @since 0.0.0
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau + 1;
	}

	/**
	 * @param tirage
	 * @return
	 * @since 0.0.0
	 */
	public Solution getSolution(Tirage tirage) {

		this.tirage = tirage;

		compte = tirage.getCompte();
		solveur.initialiser(tirage);

		solutionReference = new Solution();
		nombresDisponibles = new ArrayList<>(tirage.getNombres());
		nombresChoisis = new LinkedList<>();
		nombresChoisisReference = new ArrayList<>();

		int profondeur;

		do {

			solutionIntermediaire = null;
			nombreNombres = nombresDisponibles.size();
			profondeur = min(nombreNombres, niveau);
			nombresChoisisReference.clear();
			chercher(0, profondeur);

		} while (ameliorer());

		return solutionReference;
	}

	/**
	 * @since 0.0.0
	 */
	private boolean ameliorer() {

		boolean solutionAmelioree;

		if (nombresChoisisReference.isEmpty()
				|| solutionIntermediaire.getDistance() >= solutionReference.getDistance()) {

			solutionAmelioree = false;

		} else {

			nombresDisponibles.removeAll(nombresChoisisReference);
			nombresDisponibles.add(solutionIntermediaire.getResultat());
			solutionAmelioree = nombresDisponibles.size() > 1;
			solutionReference.concatener(solutionIntermediaire);
		}

		return solutionAmelioree;
	}

	/**
	 * @param offsetChoix
	 * @param profondeur nombre de plaques à utiliser
	 * @since 0.0.0
	 */
	public void chercher(int offsetChoix, int profondeur) {

		if (profondeur == 0) {

			var solutionCourante = chercher(nombresChoisis);

			if (solutionIntermediaire == null
					|| solutionCourante.compareTo(solutionIntermediaire) < 0) {

				solutionIntermediaire = solutionCourante;
				nombresChoisisReference.clear();
				nombresChoisisReference.addAll(nombresChoisis);
			}

		} else {

			int nombre;

			for (var indexNombre = offsetChoix; indexNombre < nombreNombres; ) {

				nombre = nombresDisponibles.get(indexNombre);
				nombresChoisis.addLast(nombre);
				chercher(++indexNombre, profondeur - 1);
				nombresChoisis.removeLast();
			}
		}
	}

	/**
	 * @param nombres
	 * @return
	 * @since 0.0.0
	 */
	public Solution chercher(List<Integer> nombres) {

		solveur.initialiser(tirage);

		var nombrePlaques = nombres.size();

		switch (nombrePlaques) {

			case 6:
				solveur.chercher(nombres.get(0), nombres.get(1), nombres.get(2), nombres.get(3), nombres.get(4),
						nombres.get(5));
				break;

			case 5:
				solveur.chercher(nombres.get(0), nombres.get(1), nombres.get(2), nombres.get(3), nombres.get(4));
				break;

			case 4:
				solveur.chercher(nombres.get(0), nombres.get(1), nombres.get(2), nombres.get(3));
				break;

			case 3:
				solveur.chercher(nombres.get(0), nombres.get(1), nombres.get(2));
				break;

			case 2:
				solveur.chercher(nombres.get(0), nombres.get(1));
				break;

			default:
				break;
		}

		var solution = solveur.getSolutionReference();
		solution.calculerDistance(compte);

		return solution;
	}
}