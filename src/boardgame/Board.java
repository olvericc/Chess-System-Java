package boardgame;

public class Board {

	// class attributes
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	// pattern builder
	public Board() {}

	// constructor with arguments
	public Board(int rows, int columns) {
		// exception
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: "
					+ "there must be at least 1 row and 1 column"
					+ "please press 'ENTER' to continue... ");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	// getters and setters
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//method Piece1()
	public Piece piece(int row, int column) {
		// defensive programming
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board"
									+ "please press 'ENTER' to continue... ");
		}
		return pieces[row][column];
	}
	
	// method Piece2()
	public Piece piece(Position position) {
		// defensive programming
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board"
									+ "please press 'ENTER' to continue... ");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	// method placePiece()
	public void placePiece(Piece piece, Position position) {
		// defensive programming
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position	
									+ "please press 'ENTER' to continue... ");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	// method removePiece()
	public Piece removePiece(Position position) {
		// defensive programming
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board"
									+ "please press 'ENTER' to continue... ");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	// auxiliar method positionExists()
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	// method positionExists()
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	// method thereIsAPiece()
	public boolean thereIsAPiece(Position position) {
		// defensive programming
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board"
									+ "please press 'ENTER' to continue... ");
		}
		return piece(position) != null;
	}
}
