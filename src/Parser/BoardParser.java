package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import src.Board;
import src.Destiny;
import src.DoubleMirror;
import src.Filter;
import src.MobileOrigin;
import src.Nothing;
import src.Origin;
import src.SemiMirror;
import src.SimpleMirror;
import src.Wall;


public class BoardParser{

	Board parsedboard;
	String filename;

	public BoardParser(String filename){
		this.filename = filename;
	}

	/**
	 * Devuelve la proxima linea que es valida en el file, es decir, linea que comienza con un caracter distitno 
	 * de # y no es vacia. Ademas borra todo espacio o tabulacion si la linea era valida
	 * @return String que sigue como linea valida en el file o null si el archivo termino 
	 * @param file Archivo a analizar
	 */
	public String getValidLine(BufferedReader file) throws IOException{
		
		String line = file.readLine();
		if (line == null)
			return null;
		line = clearSpaceAndTabs(line); 
		if (line.startsWith("#") || line.isEmpty())
			return getValidLine(file); 
		if (line.contains("#"))
			return line.substring(0,line.indexOf('#'));

		return line;
	}
		
	/**
	 * Quita espacios y tabulaciones del string.
	 * @param line
	 * @return	String sin espacios ni tabulaciones
	 */
	public String clearSpaceAndTabs(String line) {
		if (!line.isEmpty())	{
			line = line.replace(" ","");
			line = line.replace("	","");
		}
		return line;
	}
	
	private Boolean validateBoardInput(String line, int x, int y) {
		String[] strings;
		strings = line.split(",");
		if(strings.length == 2) {
			x=Integer.parseInt(strings[0]);
			y=Integer.parseInt(strings[1]);
		}
		return (x>=5 && x<=20 && y>=5 && y<=20);
	}
	
	
	private Board createParsedBoard(String line) {
		int x, y;
		if (validateBoardInput(line, x, y))
			Board board= new Board(x,y);
		return board;
	}
	
	private void createPlayer(String[] strings) {
		int x, y;
		if (strings.length!=7 || strings[3]!=0 || strings[4]!=0 || strings[5]!=0 || strings[6]!=0)
			throw new IllegalArgumentException();
		y=Integer.parseInt(strings[0]);
		x=Integer.parseInt(strings[1]);
		if( /* parsedboard.PlayerPosition != -1 && */ x>=0 && y>=0 /* && x<=parsedboard.getsizeX-2 && y<=parsedboard.getsizeY-2 */)
			parsedboard.at(x+1,y+1).put(new Player()); /* Revisar el constructor y demas */
	}
	
	
	
	private void fillParsedBoard(BufferedReader file) {
		String line;
		
		while (line=getValidLine(file))
	
	}
	
	public void parse() {
		BufferedReader file;
		String line;
		
		try{
			if (!filename.endsWith(".txt"))
				throw new IllegalArgumentException();
			file= new BufferedReader(new FileReader(filename));
			if ( (line= getValidLine(file)) == null)
				throw new IllegalArgumentException();
			parsedboard= createParsedBoard(line);
			fillParsedBoard(file);

		} 
		catch (IllegalArgumentException e) {
			
		}
		catch (FileNotFoundException e) {
			
		}
		
	}
	

}
