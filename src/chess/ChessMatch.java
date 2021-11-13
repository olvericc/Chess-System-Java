package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	// class attributes
	private Board board;
		
	// pattern builder
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
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
	
	// method initialSetup
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
	
}
