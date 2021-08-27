package com.github.achaaab.lceb.utilitaire;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import static java.awt.Image.SCALE_SMOOTH;
import static java.util.Arrays.stream;

/**
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SwingUtilitaire {

	private static final int REFERENCE_DPI = 72;
	private static final int DPI = 96;

	/**
	 * Recursively scales component hierarchy fonts.
	 *
	 * @param component component of which to scale the font
	 * @since 0.0.0
	 */
	public static void scale(Component component) {

		var minimumSize = component.getMinimumSize();
		var maximumSize = component.getMaximumSize();
		var preferredSize = component.getPreferredSize();
		var size = component.getSize();

		var scaledMinimumSize = scale(minimumSize);
		var scaledMaximumSize = scale(maximumSize);
		var scaledPreferredSize = scale(preferredSize);
		var scaledSize = scale(size);

		component.setMinimumSize(scaledMinimumSize);
		component.setMaximumSize(scaledMaximumSize);
		component.setPreferredSize(scaledPreferredSize);
		component.setSize(scaledSize);

		var font = component.getFont();

		if (font != null) {

			var fontSize = font.getSize();
			var scaledFontSize = scale(fontSize);
			var scaledFont = font.deriveFont((float) scaledFontSize);
			component.setFont(scaledFont);
		}

		if (component instanceof Container container) {

			synchronized (container.getTreeLock()) {

				var children = container.getComponents();
				stream(children).forEach(SwingUtilitaire::scale);
			}
		}

		if (component instanceof JMenu menu) {

			var popupMenu = menu.getPopupMenu();
			scale(popupMenu);
		}

		if (component instanceof AbstractButton button) {

			var icon = button.getIcon();

			if (icon instanceof ImageIcon imageIcon) {
				scale(imageIcon);
			}
		}
	}

	/**
	 * @param value
	 * @return
	 * @since 0.0.0
	 */
	private static int scale(int value) {
		return value * DPI / REFERENCE_DPI;
	}

	/**
	 * @param dimension
	 * @since 0.0.0
	 */
	private static Dimension scale(Dimension dimension) {

		if (dimension != null) {

			dimension.width = scale(dimension.width);
			dimension.height = scale(dimension.height);
		}

		return dimension;
	}

	/**
	 * @param icon
	 * @return
	 * @since 0.0.8
	 */
	private static void scale(ImageIcon icon) {

		var width = icon.getIconWidth();
		var height = icon.getIconHeight();

		var scaledWidth = scale(width);
		var scaledHeight = scale(height);

		var image = icon.getImage();
		var scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, SCALE_SMOOTH);
		icon.setImage(scaledImage);
	}
}