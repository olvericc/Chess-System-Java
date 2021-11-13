package boardgame;

public class Piece {

	// class attributes
	protected Position position;
	private Board board;
		
	// pattern builder
	public Piece() {}

	// constructor with arguments
	public Piece(Board board) {
		this.board = board;
		position = null;
	}
	
	// getters and setters
	protected Board getBoard() {
		return board;
	}
}
