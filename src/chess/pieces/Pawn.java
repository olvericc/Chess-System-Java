package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	// class attributes
	private ChessMatch chessMatch;
	
	// constructor with arguments
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	// method PossibleMoves()
	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// white pawn
		if (getColor() == Color.WHITE) {
			
			p.setValues(position.getRow() - 1, position.getColumn());
			
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				// the pawn cans move 1 layer to this position
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 2, position.getColumn());
			
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			
			if (getBoard().positionExists(p) 
					&& !getBoard().thereIsAPiece(p) 
					&& getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) 
					&& getMoveCount() == 0) {
				// the pawn cans move 2 layers to this position
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// diagonal 1
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// diagonal 2
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

		}
		
		// #special move EnPassant WHITE PIECES
		if (position.getRow() == 3) {
			// left
			Position left = new Position(position.getRow(), position.getColumn() - 1);
			if (getBoard().positionExists(left) 
					&& isThereOpponentPiece(left) 
					&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
				mat[left.getRow() - 1][left.getColumn()] = true;
			}
			// right
			Position right = new Position(position.getRow(), position.getColumn() + 1);
			if (getBoard().positionExists(right) 
					&& isThereOpponentPiece(right) 
					&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
				mat[right.getRow() - 1][right.getColumn()] = true;
			}
		}
		
		// black pawn
		else {
			
			p.setValues(position.getRow() + 1, position.getColumn());
			
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				// the pawn cans move 1 layer to this position
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() + 2, position.getColumn());
			
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			
			if (getBoard().positionExists(p) 
					&& !getBoard().thereIsAPiece(p) 
					&& getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) 
					&& getMoveCount() == 0) {
				// the pawn cans move 2 layers to this position
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// diagonal 1
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// diagonal 2
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// #special move EnPassant BLACK PIECES
			if (position.getRow() == 4) {
				// left
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) 
						&& isThereOpponentPiece(left) 
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				// right
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) 
						&& isThereOpponentPiece(right) 
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
