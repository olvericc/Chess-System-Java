package chess;

import boardgame.Position;

public class ChessPosition {

	// class attributes
	private char column;
	private int row;
	
	// pattern builder
	public ChessPosition() {}

	// constructor with arguments
	public ChessPosition(char column, int row) {
		// defensive programming
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	// getters
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	// method toPostion()
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	// method fromPosition()
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColumn()),
				8 - position.getRow());
	}

	// method toString()
	@Override
	public String toString() {
		return "" + column 
				  + row;
	}
}
