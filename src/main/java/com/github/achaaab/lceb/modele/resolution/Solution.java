package com.github.achaaab.lceb.modele.resolution;

import com.github.achaaab.lceb.utilitaire.StringUtilitaire;

import java.util.ArrayList;
import java.util.List;

import static com.github.achaaab.lceb.utilitaire.StringUtilitaire.SEPARATEUR_LIGNES;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.joining;

/**
 * Une solution est une liste d'opérations dont la dernière donne le résultat final.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Solution implements Comparable<Solution> {

	private final List<Operation> operations;

	private int nombreOperations;
	private int distance;

	/**
	 * @since 0.0.0
	 */
	public Solution() {

		operations = new ArrayList<>();
		distance = MAX_VALUE;
	}

	/**
	 * @param operations
	 * @param nombreOperations
	 * @since 0.0.0
	 */
	public Solution(List<Operation> operations, int nombreOperations) {

		this.operations = operations;
		this.nombreOperations = nombreOperations;
	}

	/**
	 * @param operation
	 * @since 0.0.0
	 */
	public void ajouterOperation(Operation operation) {

		operations.add(operation);
		nombreOperations++;
	}

	/**
	 * @since 0.0.0
	 */
	public void retirerOperation() {
		operations.remove(--nombreOperations);
	}

	/**
	 * @param compte
	 * @return
	 * @since 0.0.0
	 */
	public int calculerDistance(int compte) {

		if (nombreOperations == 0) {

			distance = MAX_VALUE;

		} else {

			var derniereOperation = operations.get(nombreOperations - 1);
			distance = abs(derniereOperation.resultat - compte);
		}

		return distance;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public int getResultat() {

		var derniereOperation = operations.get(nombreOperations - 1);
		return derniereOperation.resultat;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return
	 * @since 0.0.0
	 */
	public List<Operation> getOperations() {
		return operations;
	}

	/**
	 * @return nombre d'opérations dans la solution
	 * @since 0.0.0
	 */
	public int getNombreOperations() {
		return nombreOperations;
	}

	/**
	 * @param solution
	 * @since 0.0.0
	 */
	public void concatener(Solution solution) {

		operations.addAll(solution.operations);
		nombreOperations += solution.getNombreOperations();
		distance = solution.distance;
	}

	@Override
	public int compareTo(Solution solution) {

		int comparaison;

		if (distance == solution.distance) {
			comparaison = nombreOperations - solution.nombreOperations;
		} else {
			comparaison = distance - solution.distance;
		}

		return comparaison;
	}

	/**
	 * @return copie de la solution
	 * @since 0.0.0
	 */
	public Solution dupliquer() {
		return new Solution(new ArrayList<>(operations), nombreOperations);
	}

	@Override
	public String toString() {

		String chaine;

		if (operations.isEmpty()) {
			chaine = "aucune opération";
		} else {
			chaine = operations.stream().map(Operation::toString).collect(joining(SEPARATEUR_LIGNES));
		}

		return chaine;
	}
}