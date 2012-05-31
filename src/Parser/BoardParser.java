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
	int linecount;
	int paramcount;

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
		linecount++;
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
	
	
	/**
	 * 
	 * @param line
	 * @param x
	 * @param y
	 * @return
	 */
	private void validateBoardInput(String line, int x, int y) {
		String[] strings;
		strings = line.split(",");
		if(strings.length == 2) {
			paramcount= 1;
			x=Integer.parseInt(strings[0]);
			paramcount= 2;
			y=Integer.parseInt(strings[1]);
		}
		else 
			throw new WrongNumberOfBoardArguments;
		
		if(x<5) {
			paramcount= 1;
			throw new BoardParamIsLTExpected;
		}
		else if(x>20) {
			paramcount= 1;
			throw new BoardParamIsGTExpected;
		}
		else if(y<5)
			throw new BoardParamIsLTExpected;
		else if(y>20)
			throw new BoardParamIsGTExpected;
	}
	
	
	/**
	 * 
	 * @param line
	 * @return
	 */
	private Board createParsedBoard(String line) {
		int x, y;
		validateBoardInput(line, x, y);
		Board board= new Board(x,y);
		return board;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Board getParsedBoard() {
		return parsedboard;
	}
	
	
	/**
	 * 
	 * @param file
	 */
	private void fillParsedBoard(BufferedReader file) {
		int i;
		String line;
		String[] unparsedints;
		int[] parsedints;
		Creable[] creationarray;

		creationarray[0]= new Creable() {
			@Override
			public void create(int[] arr) {
				if(/* se cumplen ciertas condiciones*/ true)
					new Player(arr[0], arr[1]);
			}
		};
		creationarray[1]= new Creable() {
			@Override
			public void create(int[] arr) {
				if(/* se cumplen ciertas condiciones*/ true)
					new Box(arr[0], arr[1], arr[4], arr[5], arr[6]);
			}
		};
		creationarray[2]= new Creable() {
			@Override
			public void create(int[] arr) {
				if(/* se cumplen ciertas condiciones*/ true)
					new Target(arr[0], arr[1], arr[4], arr[5], arr[6]);
			}
		};
		creationarray[3]= new Creable() {
			@Override
			public void create(int[] arr) {
				if(/* se cumplen ciertas condiciones*/ true)
					new Wall(arr[0], arr[1]);
			}
		};
		creationarray[4]= new Creable() {
			@Override
			public void create(int[] arr) {
				if(/* se cumplen ciertas condiciones*/ true)
					new BlackHole(arr[0], arr[1]);
			}
		};
		creationarray[5]= new Creable() {
			@Override
			public void create(int[] arr) {
				if(/* se cumplen ciertas condiciones*/ true)
					new BombBox(arr[0], arr[1], arr[3], arr[4], arr[5], arr[6]);
			}
		};
		
		while ( (line=getValidLine(file)) != null) {
			unparsedints= line.split(",");
			if(unparsedints.length != 7)
				throw new WrongNumberOfArgumentsException;
			for(i=0; i<7 ;i++) {
				parsedints[i]= Integer.parseInt(unparsedints[i]);
			}
			creationarray[parsedints[2]-1].create(parsedints);
		}
	
	}
	
	
	/**
	 * 
	 */
	public void parse() {
		BufferedReader file;
		String line;
		
		try{
			if(!filename.endsWith(".txt"))
				throw new IncorrectFileExtensionException;
			file= new BufferedReader(new FileReader(filename));
			if ( (line= getValidLine(file)) == null)
				throw new EmptyFileException();
			parsedboard= createParsedBoard(line);
			fillParsedBoard(file);

		} 
		catch (IllegalArgumentException e) {
			
		}
	}
	

}
