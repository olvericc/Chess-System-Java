package chess;

import boardgame.Board;

public class ChessMatch {

	// class attributes
	private Board board;
		
	// pattern builder
	public ChessMatch() {
		board = new Board(8,8);
	}
	
	// method getPiece
	public ChessPiece[][] getPieces(){
		// creating matrix
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			
			for (int j = 0; j < board.getColumns(); j++) {
				// DOWNCASTING
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
}
