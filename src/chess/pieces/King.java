package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	// constructor with arguments
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	// method toString()
	@Override
	public String toString() {
		return "K";
	}

	// aux method CanMove()
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	// mehtod testRookCastling()
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}
	
	// method possibleMoves()
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// positions: above, below, left, right
		// above my piece
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below my piece
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left my piece
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right my piece
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// positions: northwest, northeast, southwest, swotheast
		// northwest my piece -> nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// northeast my piece -> ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// southwest my piece -> sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// southeast my piece -> se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// #special move Castling
		
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// #special move Castling -> king side rook
			Position posT1 = new Position(position.getRow(), position.getColumn()  + 3);
			if (testRookCastling(posT1)) {
				// 1 right
				Position p1 = new Position(position.getRow(), position.getColumn()  + 1);
				// 2 right
				Position p2 = new Position(position.getRow(), position.getColumn()  + 2);
				
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}// if
			}// if	
			
			// #special move Castling -> queen side rook
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posT2)) {
				// 1 left
				Position p1 = new Position(position.getRow(), position.getColumn()  - 1);
				// 2 left
				Position p2 = new Position(position.getRow(), position.getColumn()  - 2);
				// 3 left
				Position p3 = new Position(position.getRow(), position.getColumn()  - 3);
				
				if (getBoard().piece(p1) == null 
						&& getBoard().piece(p2) == null 
						&& getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}// if
			}// if	
		}// if
		
		return mat;
	}
}
