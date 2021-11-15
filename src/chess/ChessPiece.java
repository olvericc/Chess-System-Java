package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	// class attributes
	private Color color;

	// pattern builder
	public ChessPiece() {
		super();
	}
	
	// constructor with arguments super()1
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	// getters and setters
	public Color getColor() {
		return color;
	}
	
	// method IsThereOpponentPiece
	protected boolean isThereOpponentPiece(Position position) {
		// DOWNCASTING
		ChessPiece p =(ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
}
	
