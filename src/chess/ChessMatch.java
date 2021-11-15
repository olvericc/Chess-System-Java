package chess;

import boardgame.Board;
import boardgame.Piece;
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
	
	// method PossibleMoves
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).PossibleMoves();
	}
	
	// method performChessMove
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		// operation validateSourcePosition
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		// DOWNCASTING
		return (ChessPiece)capturedPiece; 
	}

	// method makeMove
	private Piece makeMove(Position source, Position target) {
			Piece p = board.removePiece(source);
			Piece capturedPiece = board.removePiece(target);
			board.placePiece(p, target);
			return capturedPiece;
	}

	// method validateSourcePosition
	private void validateSourcePosition(Position position) {
		// defensive programming
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move for the choosen piece");
		}
	}
	
	// method validateTargetPosition
	private void validateTargetPosition(Position source, Position target) {
		// defensive programming
		if (!board.piece(source).possibleMoves(target)) {
			throw new ChessException("The choosen piece can't move to target position");
		}
		
	}
	
	// method placeNewPiece
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	// method initialSetup
	private void initialSetup() {
		// color white
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        
        // color black
        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
}
