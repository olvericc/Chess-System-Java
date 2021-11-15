package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	// constructor with arguments
	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	// method toString
	@Override
	public String toString() {
		return "R";
	}
	
	// method PossibleMove
	@Override
	public boolean[][] PossibleMove() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
}
