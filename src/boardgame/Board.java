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
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	// getters and setters
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
}
