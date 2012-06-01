package Parser;

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
	private String getValidLine(BufferedReader file) {
		
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
	private String clearSpaceAndTabs(String line) {
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
			throw new WrongNumberOfBoardArgumentsException;
		
		if(x<5) {
			paramcount= 1;
			throw new BoardParamIsLTExpectedException;
		}
		else if(x>20) {
			paramcount= 1;
			throw new BoardParamIsGTExpectedException;
		}
		else if(y<5)
			throw new BoardParamIsLTExpectedException;
		else if(y>20)
			throw new BoardParamIsGTExpectedException;
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
	
	private void playerIsPresent() {
		if(/* Player not present */)
			throw new MissingPlayerException;
	}
	
	
	/**
	 * 
	 */
	private void validatePlayer(int[] arr) {
		int[] zeros;
		zeros[0]=3;
		zeros[1]=4;
		zeros[2]=5;
		zeros[3]=6;
		validateZeroFilledArguments(arr, zeros);
	}
	
		
	private void validateZeroFilledArguments(int[] arr, int[] indexes) {
		int i;
	
		for(i=indexes[0]; i<indexes.lenght() ;i++) {
			paramcount=i;
			if (arr[i] != 0)
				throw new ParamNotZeroException;
		}
	}
		
	/**
	 * 
	 */
	private void validColors(int[] arr) {
		int i;
		
		for(i=4; i<7 ;i++) {
			paramcount= i;
			if(arr[i]<0)
				throw new ParamIsLTExpectedException;
			else if (arr[i]>255)
				throw new ParamIsGTExpectedException;
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
				validatePlayer(arr);
				Player p= new Player();
			}
		};
		creationarray[1]= new Creable() {
			@Override
			public void create(int[] arr) {
				validateBox(arr);
				Box b= new Box(arr[4], arr[5], arr[6]);
			}
		};
		creationarray[2]= new Creable() {
			@Override
			public void create(int[] arr) {
				validateTarget(arr);
				Target t=new Target(arr[4], arr[5], arr[6]);
			}
		};
		creationarray[3]= new Creable() {
			@Override
			public void create(int[] arr) {
				validateWall(arr);
				Wall w= new Wall();
			}
		};
		creationarray[4]= new Creable() {
			@Override
			public void create(int[] arr) {
				validateBlackHole(arr);
				BlackHole bh= new BlackHole();
			}
		};
		creationarray[5]= new Creable() {
			@Override
			public void create(int[] arr) {
				validateBombBox(arr);
				BombBox bb= new BombBox(arr[3], arr[4], arr[5], arr[6]);
			}
		};
		
		while ( (line=getValidLine(file)) != null) {
			unparsedints= line.split(",");
			if(unparsedints.length != 7)
				throw new WrongNumberOfArgumentsException;
			for(i=0; i<7 ;i++) {
				paramcount= i;
				parsedints[i]= Integer.parseInt(unparsedints[i]);
			}
			creationarray[parsedints[2]-1].create(parsedints);
		}
		playerIsPresent();
	}
	
	
	/**
	 * 
	 * 
	 */
	public void parse() {
		BufferedReader file;
		String line;

		if(!filename.endsWith(".txt"))
			throw new IncorrectFileExtensionException;
		file= new BufferedReader(new FileReader(filename));
		if ( (line= getValidLine(file)) == null)
			throw new EmptyFileException();
		parsedboard= createParsedBoard(line);
		fillParsedBoard(file);
	}
	
}
