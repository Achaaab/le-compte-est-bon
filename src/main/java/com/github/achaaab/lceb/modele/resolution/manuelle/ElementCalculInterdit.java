package com.github.achaaab.lceb.modele.resolution.manuelle;

/**
 * exception à lever lorsqu'un élément de calcul utilisé est interdit
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class ElementCalculInterdit extends Exception {

	/**
	 * @param message
	 * @since 0.0.0
	 */
	public ElementCalculInterdit(String message) {
		super(message);
	}
}