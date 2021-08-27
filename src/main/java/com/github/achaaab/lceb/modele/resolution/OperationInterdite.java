package com.github.achaaab.lceb.modele.resolution;

/**
 * exception à lever lorsqu'une operation est interdite (division par 1, multiplication par 1, ...)
 * 
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class OperationInterdite extends Exception {

	/**
	 * @param raison
	 * @since 0.0.0
	 */
	public OperationInterdite(String raison) {
		super("Cette opération est interdite. " + raison);
	}
}