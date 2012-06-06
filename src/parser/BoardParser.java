package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import backEnd.*;

public class BoardParser{

	Board parsedboard;
	String filename;
	int linecount;
	int paramcount;

	public BoardParser(String filename){
		this.filename = filename;
	}

	
	/**
	 * Devuelve la proxima linea valida, borrando todo espacio o tabulacion.
	 * @return String que sigue como linea valida en el file o null si el archivo termino 
	 * @param file Archivo a analizar
	 * @throws EmptyFileException 
	 * @throws EmptyArgumentException 
	 */
	private String getValidLine(BufferedReader file) throws EmptyFileException, EmptyArgumentException {
		
		String line= null;
		try	{ line = file.readLine();
			if (line == null)
				return line;
			linecount ++;
			line = clearSpaceAndTabs(line); 
			if (line.startsWith("#") || line.isEmpty() )
				return getValidLine(file);
			if (line.contains("#"))
				line= line.substring(0,line.indexOf('#'));
		} catch (IOException e) { ; }
		if(line.contains(",,") || line.endsWith(","))
			throw new EmptyArgumentException();
		
		return line;
	}
		
	
	/**
	 * Quita espacios y tabulaciones.
	 * @param line
	 */
	private String clearSpaceAndTabs(String line) {
		line = line.replace(" ","");
		line = line.replace("	","");
		return line;
	}
	
	
	/**
	 * Valida el tamaño del tablero.
	 * @throws WrongNumberOfBoardArgumentsException 
	 * @throws BoardParamIsLTExpectedException 
	 * @throws BoardParamIsGTExpectedException 
	 */
	private void validateBoardSize(int x, int y) throws WrongNumberOfBoardArgumentsException, BoardParamIsLTExpectedException, BoardParamIsGTExpectedException {
		if(x<5) {
			paramcount= 1;
			throw new BoardParamIsLTExpectedException();
		}
		else if(x>20) {
			paramcount= 1;
			throw new BoardParamIsGTExpectedException();
		}
		else if(y<5) {
			paramcount= 2;
			throw new BoardParamIsLTExpectedException();
		}
		else if(y>20) {
			paramcount= 2;
			throw new BoardParamIsGTExpectedException();
		}
	}
	
	
	/**
	 * Valida la linea en que se crea de tablero y lo construye. 
	 * @throws BoardParamIsGTExpectedException 
	 * @throws BoardParamIsLTExpectedException 
	 * @throws WrongNumberOfBoardArgumentsException 
	 */
	private Board createParsedBoard(String line) throws WrongNumberOfBoardArgumentsException, BoardParamIsLTExpectedException, BoardParamIsGTExpectedException {
		int x, y;
		String[] strings;
		strings = line.split(",");
		if(strings.length == 2) {
			paramcount= 1;
			x=Integer.parseInt(strings[0]);
			paramcount= 2;
			y=Integer.parseInt(strings[1]);
		}
		else 
			throw new WrongNumberOfBoardArgumentsException();

		validateBoardSize(x,y);
		Board board= new Board(x,y);
		return board;
	}
	
	/**
	 * Siempre invocar el metodo parse antes de getParsedBoard, ya que de lo contrario el tablero no estara parseado.
	 * @return el tablero.
	 */
	public Board getParsedBoard() {
		return parsedboard;
	}
	
	/**
	 * Valida la presencia del jugador.
	 * @throws MissingPlayerException
	 */
	private void isPlayerPresent() throws MissingPlayerException {
		if (! parsedboard.isPlayerPresent())
			throw new MissingPlayerException();
	}
	
	/**
	 * Valida la ausencia del jugador.
	 * @throws PlayerAlreadyDeclaredException
	 */
	private void isPlayerAbscent() throws PlayerAlreadyDeclaredException {
		if (parsedboard.isPlayerPresent())
			throw new PlayerAlreadyDeclaredException();
	}
	
	/**
	 * @return el numero de parametro en el que se encontro el error.
	 */
	public int getParamNumber() {
		return paramcount;
	}
	
	/**
	 * @return el numero de linea en el que se encontro el error.
	 */
	public int getLineNumber() {
		return linecount;
	}
	
	/**
	 * Valida la linea que define un jugador.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @throws ParamNotZeroException 
	 */
	private void validatePlayer(int[] arr) throws ParamNotZeroException {
		int[] zeros = new int[4];
		zeros[0]=3;
		zeros[1]=4;
		zeros[2]=5;
		zeros[3]=6;
		validateZeroFilledArguments(arr, zeros);
	}
	
	/**
	 * Valida la linea que define una caja.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @return el color que posee la caja.
	 * @throws ParamNotZeroException
	 * @throws IllegalColorException
	 * @throws ParamIsNegativeException
	 */
	private Color validateBox(int[] arr) throws ParamNotZeroException, IllegalColorException, ParamIsNegativeException {

		if(arr[3] != 0) {
			paramcount= 4;
			throw new ParamNotZeroException();
		}
		return validateBombBox(arr);
	}
	
	/**
	 * Valida la linea que define un destino.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @return el color que posee el destino.
	 * @throws ParamNotZeroException
	 * @throws IllegalColorException
	 * @throws ParamIsNegativeException
	 */
	private Color validateTarget(int[] arr) throws ParamNotZeroException, IllegalColorException, ParamIsNegativeException {
	
		return validateBox(arr);
	}
	
	/**
	 * Valida la linea que define una pared.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @throws ParamNotZeroException
	 */
	private void validateWall(int[] arr) throws ParamNotZeroException {
	
		validatePlayer(arr);
	}
	
	/**
	 * Valida la linea que define un pozo.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @throws ParamNotZeroException
	 */
	private void validateBlackHole(int[] arr) throws ParamNotZeroException {
		
		validateWall(arr);
	}
	
	/**
	 * Valida la linea que define una caja bomba.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @return el color que posee la caja bomba.
	 * @throws ParamNotZeroException
	 * @throws IllegalColorException
	 * @throws ParamIsNegativeException
	 */
	private Color validateBombBox(int[] arr) throws ParamNotZeroException, IllegalColorException, ParamIsNegativeException {
		Color c;
		try{ c=new Color(arr[4], arr[5], arr[6]); }
		catch (IllegalArgumentException e) { throw new IllegalColorException(); }
		if(arr[3]<0) {
			paramcount=4;
			throw new ParamIsNegativeException();
		}
		return c;	
	}
		
		
	/**
	 * Valida que haya 0 en los datos de creacion ya parseados, en los indices elegidos.
	 * @param arr contiene los datos de creacion ya parseados.
	 * @param indexes contiene los indices en los cuales los datos deben tener un 0.
	 * @throws ParamNotZeroException
	 */
	private void validateZeroFilledArguments(int[] arr, int[] indexes) throws ParamNotZeroException {
		int i;
	
		for(i=0; i<indexes.length ;i++) {
			paramcount=indexes[i] +1;
			if (arr[indexes[i]] != 0)
				throw new ParamNotZeroException();
		}
	}
		
	/**
	 * Rellena el tablero que se creo previamente, con el jugador, las cajas y demas, siguiendo lo descripto en el archivo.
	 * @param file archivo inicial menos la linea de creacion del tablero.
	 * @throws WrongNumberOfArgumentsException 
	 * @throws NumberFormatException 
	 * @throws MissingPlayerException 
	 * @throws PlayerAlreadyDeclaredException 
	 * @throws EmptyFileException 
	 * @throws EmptyArgumentException 
	 * @throws ParamIsNegativeException 
	 * @throws WrongObjectTypeException 
	 */
	private void fillParsedBoard(BufferedReader file) throws WrongNumberOfArgumentsException, NumberFormatException, MissingPlayerException, ParamNotZeroException, IllegalColorException, PlayerAlreadyDeclaredException, EmptyFileException, EmptyArgumentException, ParamIsNegativeException, WrongObjectTypeException {
		int i;
		String line;
		String[] unparsedints;
		int[] parsedints= new int[7];
		Creable[] creationarray= new Creable[6];

		creationarray[0]= new Creable() {
			@Override
			public void create(int[] arr) throws ParamNotZeroException, PlayerAlreadyDeclaredException {
				validatePlayer(arr);
				isPlayerAbscent();
				Player p= new Player();
				parsedboard.vPutAt(arr[0], arr[1], p);
			}
		};
		creationarray[1]= new Creable() {
			@Override
			public void create(int[] arr) throws ParamNotZeroException, IllegalColorException, ParamIsNegativeException {
				Color c= validateBox(arr);
				Box b= new Box(c);
				parsedboard.vPutAt(arr[0], arr[1], b);
			}
		};
		creationarray[2]= new Creable() {
			@Override
			public void create(int[] arr) throws ParamNotZeroException, IllegalColorException, ParamIsNegativeException {
				Color c= validateTarget(arr);
				Target t=new Target(c);
				parsedboard.vPutAt(arr[0], arr[1], t);
			}
		};
		creationarray[3]= new Creable() {
			@Override
			public void create(int[] arr) throws ParamNotZeroException {
				validateWall(arr);
				Wall w= new Wall();
				parsedboard.vPutAt(arr[0], arr[1], w);
			}
		};
		creationarray[4]= new Creable() {
			@Override
			public void create(int[] arr) throws ParamNotZeroException {
				validateBlackHole(arr);
				BlackHole bh= new BlackHole();
				parsedboard.vPutAt(arr[0], arr[1], bh);
			}
		};
		creationarray[5]= new Creable() {
			@Override
			public void create(int[] arr) throws ParamNotZeroException, IllegalColorException, ParamIsNegativeException {
				Color c= validateBombBox(arr);
				BombBox bb= new BombBox(c,arr[3]);
				parsedboard.vPutAt(arr[0], arr[1], bb);
			}
		};
		
		while ( (line=getValidLine(file)) != null) {
			unparsedints= line.split(",");
			
			if(unparsedints.length != 7)
				throw new WrongNumberOfArgumentsException();
			for(i=0; i<7 ;i++) {
				paramcount= i+1;
				parsedints[i]= Integer.parseInt(unparsedints[i]);
			}
			if(parsedints[2]>0 && parsedints[2]<7)
				creationarray[parsedints[2]-1].create(parsedints);
			else
				throw new WrongObjectTypeException();
		}
		isPlayerPresent();
	}
	
	
	/** Parsea el archivo. Debe ser llamada antes de invocar getParsedBoard(), ya que de lo contrario el tablero no estara parseado.
	 * @throws IncorrectFileExtensionException 
	 * @throws EmptyFileException 
	 * @throws BoardParamIsGTExpectedException 
	 * @throws BoardParamIsLTExpectedException 
	 * @throws WrongNumberOfArgumentsException 
	 * @throws NumberFormatException 
	 * @throws MissingPlayerException 
	 * @throws IllegalColorException 
	 * @throws ParamNotZeroException 
	 * @throws PlayerAlreadyDeclaredException 
	 * @throws FileNotFoundException 
	 * @throws EmptyArgumentException 
	 * @throws ParamIsNegativeException 
	 * @throws WrongObjectTypeException 
	 * 
	 * 
	 */
	public void parse() throws IncorrectFileExtensionException, EmptyFileException, BoardParamIsLTExpectedException, BoardParamIsGTExpectedException, NumberFormatException, WrongNumberOfArgumentsException, MissingPlayerException, ParamNotZeroException, IllegalColorException, PlayerAlreadyDeclaredException, FileNotFoundException, EmptyArgumentException, ParamIsNegativeException, WrongObjectTypeException {
		BufferedReader file;
		String line;

		if(!filename.endsWith(".txt"))
			throw new IncorrectFileExtensionException();
		file= new BufferedReader(new FileReader(filename));
		if ( (line= getValidLine(file)) == null)
			throw new EmptyFileException();
		parsedboard= createParsedBoard(line);
		fillParsedBoard(file);
	}
	
}
