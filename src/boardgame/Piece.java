package boardgame;

public abstract class Piece {

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
	
	// method possibleMoves() boolean
	public abstract boolean[][] possibleMoves();
	
	// method possibleMoves() (testing)
	public boolean possibleMoves(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	// method isThereAnyPossibleMove()
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		
		for (int i = 0; i < mat.length; i++) {
			
			for (int j = 0; j < mat.length; j++) {
				
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
