package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Clase con métodos útiles para el manejo de imágenes.
 */
public class ImageUtils {

	/**
	 * Carga una imagen y retorna una instancia de la misma. Si hay algun problema al leer el archivo lanza una excepcion.
	 */
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {
			return ImageIO.read(new File(fileName));
		} else {
			return ImageIO.read(stream);
		}
	}


	/**
	 * Dada una imagen en escala de grises, la tiñe con el color indicado.
	 */
	public static Image colorize(Image image, Color color) {
		BufferedImage result = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		result.getGraphics().drawImage(image, 0, 0, null);

		for (int x = 0; x < image.getWidth(null); x++) {
			for (int y = 0; y < image.getHeight(null); y++) {
				Color c = new Color(result.getRGB(x, y), true);

				if (c.getAlpha() != 0) {
					double r = c.getGreen() / 255.0;
					Color c2 = new Color((int) (r * color.getRed()), (int) (r * color.getGreen()), (int) (r * color.getBlue()));
					result.setRGB(x, y, c2.getRGB());
				}
			}
		}
		return result;

	}

	/**
	 * Aumenta el brillo de una imagen.
	 */
	public static Image increaseBrightness(Image image) {
		BufferedImage result = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		result.getGraphics().drawImage(image, 0, 0, null);

		for (int x = 0; x < image.getWidth(null); x++) {
			for (int y = 0; y < image.getHeight(null); y++) {
				Color c = new Color(result.getRGB(x, y));
				result.setRGB(x, y, new Color(verifyRange(c.getRed() + 100), verifyRange(c.getGreen() + 100),
						verifyRange(c.getBlue() + 100)).getRGB());
			}
		}
		return result;

	}

	/**
	 * Dibuja un texto en el centro de la imagen, con el
	 * color indicado. Retorna una imagen nueva con los cambios, la imagen
	 * original no se modifica.
	 */
	public static Image drawString(Image img, String text, Color color) {
		BufferedImage result = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		g.drawImage(img, 0, 0, null);

		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		Rectangle2D r = font.getStringBounds(text, g.getFontRenderContext());
		g.setFont(font);

		g.setColor(color);
		g.drawString(text, img.getWidth(null) / 2 - (int) r.getWidth() / 2,
				img.getHeight(null) / 2 + (int) r.getHeight() / 2 - 2);
		return result;
	}

	private static int verifyRange(double value) {
		if (value < 0) {
			return 0;
		} else if (value > 255) {
			return 255;
		} else {
			return (int) value;
		}
	}
}