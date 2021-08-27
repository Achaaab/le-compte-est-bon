package com.github.achaaab.lceb.modele.resolution.automatique;

import com.github.achaaab.lceb.modele.Tirage;
import com.github.achaaab.lceb.modele.resolution.Addition;
import com.github.achaaab.lceb.modele.resolution.Division;
import com.github.achaaab.lceb.modele.resolution.Multiplication;
import com.github.achaaab.lceb.modele.resolution.Operation;
import com.github.achaaab.lceb.modele.resolution.Solution;
import com.github.achaaab.lceb.modele.resolution.Soustraction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class Solveur {

	private int compte;
	private Solution solution;
	private Solution solutionReference;
	private int distanceReference;
	private int rangReference;

	/**
	 * @param tirage
	 * @return solution de référence (qui n'est pas forcément la meilleure solution)
	 * obtenue en parcourant tout ou partie de l'arbre des solutions
	 * @since 0.0.0
	 */
	public Solution getSolution(Tirage tirage) {

		initialiser(tirage);

		var indexNombre = 0;

		var a = tirage.getNombre(indexNombre++);
		var b = tirage.getNombre(indexNombre++);
		var c = tirage.getNombre(indexNombre++);
		var d = tirage.getNombre(indexNombre++);
		var e = tirage.getNombre(indexNombre++);
		var f = tirage.getNombre(indexNombre);

		chercher(a, b, c, d, e, f);

		return solutionReference;
	}

	/**
	 * @param tirage
	 * @since 0.0.0
	 */
	public void initialiser(Tirage tirage) {

		compte = tirage.getCompte();
		solution = new Solution();
		solutionReference = null;
		distanceReference = MAX_VALUE;
		rangReference = MAX_VALUE;
	}

	/**
	 * Liste les opérations possibles et utiles avec les opérandes a et b.
	 *
	 * @param a
	 * @param b
	 * @since 0.0.0
	 */
	private List<Operation> getOperations(int a, int b) {

		var operations = new ArrayList<Operation>();

		operations.add(new Addition(a, b));

		if (a > b) {
			operations.add(new Soustraction(a, b));
		} else if (a < b) {
			operations.add(new Soustraction(b, a));
		}

		if (a != 1 && b != 1) {
			operations.add(new Multiplication(a, b));
		}

		if (b != 1 && a % b == 0 && b * b != a) {
			operations.add(new Division(a, b));
		} else if (a != 1 && b % a == 0 && a * a != b) {
			operations.add(new Division(b, a));
		}

		return operations;
	}

	/**
	 * Teste la solution courante et l'enregistre si elle est meilleure que la solution de référence.
	 *
	 * @param resultat résultat de la solution courante
	 * @param rang nombre d'opérations qu'il a fallu pour parvenir au résultat
	 * @since 0.0.0
	 */
	private void testerSolution(int resultat, int rang) {

		var distance = abs(compte - resultat);

		if (distance < distanceReference || distance == distanceReference && rang < rangReference) {

			solutionReference = solution.dupliquer();
			distanceReference = distance;
			rangReference = rang;
		}
	}

	/**
	 * methode optimisée pour effectuer un parcours des solutions dans un arbre de hauteur 2
	 *
	 * @param a
	 * @param b
	 * @since 0.0.0
	 */
	public void chercher(int a, int b) {

		var operations = getOperations(a, b);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 5);
			solution.retirerOperation();
		}
	}

	/**
	 * methode optimisée pour effectuer un parcours des solutions dans un arbre de hauteur 3
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @since 0.0.0
	 */
	public void chercher(int a, int b, int c) {

		var operations = getOperations(a, b);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 4);
			chercher(c, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 4);
			chercher(b, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 4);
			chercher(a, operation.resultat);
			solution.retirerOperation();
		}
	}

	/**
	 * methode optimisée pour effectuer un parcours des solutions dans un arbre de hauteur 4
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @since 0.0.0
	 */
	public void chercher(int a, int b, int c, int d) {

		var operations = getOperations(a, b);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 3);
			chercher(c, d, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 3);
			chercher(b, d, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 3);
			chercher(b, c, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 3);
			chercher(a, d, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 3);
			chercher(a, c, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(c, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 3);
			chercher(a, b, operation.resultat);
			solution.retirerOperation();
		}
	}

	/**
	 * methode optimisée pour effectuer un parcours des solutions dans un arbre de hauteur 5
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @since 0.0.0
	 */
	public void chercher(int a, int b, int c, int d, int e) {

		var operations = getOperations(a, b);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(c, d, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(b, d, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(b, c, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(b, c, d, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(a, d, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(a, c, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(a, c, d, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(c, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(a, b, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(c, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(a, b, d, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(d, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 2);
			chercher(a, b, c, operation.resultat);
			solution.retirerOperation();
		}
	}

	/**
	 * methode optimisée pour effectuer un parcours des solutions dans un arbre de hauteur 6
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @since 0.0.0
	 */
	public void chercher(int a, int b, int c, int d, int e, int f) {

		var operations = getOperations(a, b);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(c, d, e, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(b, d, e, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(b, c, e, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(b, c, d, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(a, f);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(b, c, d, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, c);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, d, e, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, c, e, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, c, d, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(b, f);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, c, d, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(c, d);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, b, e, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(c, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, b, d, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(c, f);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, b, d, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(d, e);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, b, c, f, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(d, f);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, b, c, e, operation.resultat);
			solution.retirerOperation();
		}

		operations = getOperations(e, f);

		for (var operation : operations) {

			solution.ajouterOperation(operation);
			testerSolution(operation.resultat, 1);
			chercher(a, b, c, d, operation.resultat);
			solution.retirerOperation();
		}
	}

	/**
	 * @return solution de référence
	 * @since 0.0.0
	 */
	public Solution getSolutionReference() {
		return solutionReference;
	}
}