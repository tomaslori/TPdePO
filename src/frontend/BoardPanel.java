package frontend;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Panel que permite representar un tablero de N x M celdas.
 * Permite colocar imagenes en las celdas y superponer imagenes con transparencia.
 */
public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int cellSize;
	private int rows, columns;
	private Image[][] cells;
	
	/**
	 * Crea un nuevo panel con una cantidad determinada de filas y columnas. El parametro cellSize
	 * indica el ancho y alto en pixels de las celdas.
	 */
	public BoardPanel(int rows, int columns, int cellSize) {
		this.rows = rows;
		this.columns = columns;
		this.cellSize = cellSize;
		this.cells = new Image[rows][columns];

		setSize(columns * cellSize + 1, rows * cellSize + 1);
	}

	/**
	 * Elimina el contenido de una celda determinada.
	 * Para que el cambio se vea reflejado es necesario invocar al metodo repaint.
	 */
	public void clearImage(int row, int column) {
		cells[row][column] = null;
	}
	
	/**
	 * Coloca una imagen en una celda determinada. Si la celda ya contenía otra imagen,
	 * la reemplaza. Para que el cambio se vea reflejado es necesario invocar al metodo repaint.
	 */

	public void setImage(int row, int column, Image image) {
		cells[row][column] = new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB);
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}
	
	/**
	 * Superpone una imagen sobre una celda. Si la celda está vacía, es equivalente a 
	 * usar {@code setImage}. Si la celda no está vacía y la imagen a superponer contiene transparencias,
	 * entonces se superpone la imagen encima de la existente.
	 * Para que el cambio se vea reflejado es necesario invocar al metodo repaint.
	 */
	public void appendImage(int row, int column, Image image) {
		if (cells[row][column] == null) {
			cells[row][column] = new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB);
		}
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}
	
	/**
	 * @see JPanel#paint(Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cells[i][j] != null) {
					g.drawImage(cells[i][j], j * cellSize, i * cellSize, null);
				}
			}
		}
	}
	
}