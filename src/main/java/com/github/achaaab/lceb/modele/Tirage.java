package com.github.achaaab.lceb.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Tirage {

	/**
	 * nombre de plaques dans chaque tirage
	 *
	 * @since 0.0.0
	 */
	public static final int NOMBRE_PLAQUES = 6;

	/**
	 * nombres possibles pour chaque tirage
	 *
	 * @since 0.0.0
	 */
	public static final List<Integer> PLAQUES;

	static {

		PLAQUES = new ArrayList<>();

		for (var nombre = 1; nombre <= 10; nombre++) {

			PLAQUES.add(nombre);
			PLAQUES.add(nombre);
		}

		PLAQUES.add(25);
		PLAQUES.add(50);
		PLAQUES.add(75);
		PLAQUES.add(100);
	}

	/**
	 * nombres possibles uniques pour chaque tirage
	 *
	 * @since 0.0.0
	 */
	public static final Integer[] NOMBRES_POSSIBLES_UNIQUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 25, 50, 75, 100};

	private static final Random RANDOM = new Random();

	private final List<Integer> nombres;
	private int compte;

	/**
	 * @since 0.0.0
	 */
	public Tirage() {

		nombres = new ArrayList<>();

		while (nombres.size() < NOMBRE_PLAQUES) {
			nombres.add(NOMBRES_POSSIBLES_UNIQUES[0]);
		}

		compte = 999;
	}

	/**
	 * @since 0.0.0
	 */
	public void genererAleatoirement() {

		shuffle(PLAQUES);

		for (var indexPlaque = 0; indexPlaque < NOMBRE_PLAQUES; indexPlaque++) {
			setNombre(indexPlaque, PLAQUES.get(indexPlaque));
		}

		compte = 100 + RANDOM.nextInt(900);
	}

	/**
	 * @param index
	 * @param nombre
	 * @since 0.0.0
	 */
	public void setNombre(int index, int nombre) {
		nombres.set(index, nombre);
	}

	/**
	 * @param index
	 * @return
	 * @since 0.0.0
	 */
	public int getNombre(int index) {
		return nombres.get(index);
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public List<Integer> getNombres() {
		return nombres;
	}

	/**
	 * @param compte
	 * @since 0.0.0
	 */
	public void setCompte(int compte) {
		this.compte = compte;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public int getCompte() {
		return compte;
	}

	@Override
	public String toString() {
		return nombres + " --> " + compte;
	}
}