package com.github.achaaab.lceb.utilitaire;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import static javax.imageio.ImageIO.read;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class FichierUtilitaire {

	private static final ClassLoader CHARGEUR_CLASSES = FichierUtilitaire.class.getClassLoader();

	/**
	 * @param chemin
	 * @return
	 * @since 0.0.0
	 */
	public static URL getUrlRessource(String chemin) {
		return CHARGEUR_CLASSES.getResource(chemin);
	}

	/**
	 * @param chemin chemin de la ressource contenant l'image
	 * @return
	 * @throws IOException
	 * @since 0.0.0
	 */
	public static Image getImage(String chemin) throws IOException {

		var urlImage = getUrlRessource(chemin);
		return read(urlImage);
	}

	/**
	 * @param chemin
	 * @return
	 * @throws IOException
	 * @since 0.0.0
	 */
	public static Icon getIcone(String chemin) throws IOException {

		var image = getImage(chemin);
		return new ImageIcon(image);
	}
}