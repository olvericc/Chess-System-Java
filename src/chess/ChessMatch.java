package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	// class attributes
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;

	// Lists
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	// pattern builder
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		// check = false;
		initialSetup();
	}

	// getters
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	// method getPiece()
	public ChessPiece[][] getPieces() {
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

	// method PossibleMoves()
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).PossibleMoves();
	}

	// method performChessMove()
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		// operation validateSourcePosition
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);

		// testing check
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}

		check = (testCheck(opponent(currentPlayer))) ? true : false;

		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		// DOWNCASTING
		return (ChessPiece) capturedPiece;
	}

	// method makeMove()
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	// method UndoMove()
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	// method validateSourcePosition()
	private void validateSourcePosition(Position position) {
		// defensive programming
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position!" + " please press 'ENTER' to continue... ");
		}
		// DOWNCASTING
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The choosen piece is not yours!" + " please press 'ENTER' to continue... ");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException(
					"There is no possible move for the choosen piece!" + " please press 'ENTER' to continue... ");
		}
	}

	// method validateTargetPosition()
	private void validateTargetPosition(Position source, Position target) {
		// defensive programming
		if (!board.piece(source).possibleMoves(target)) {
			throw new ChessException(
					"The choosen piece can't move to target position! " + "please press 'ENTER' to continue... ");
		}
	}

	// method placeNewPiece()
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	// method NextTurn()
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// method Opponent()
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// method King(Color)
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				// DOWNCASTING
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no " + color + "king on the board");
	}

	// method TestCheck()
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

		for (Piece p : opponentPieces) {
			boolean[][] mat = p.PossibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	// method TestCheckMate()
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream()
						   .filter(x -> ((ChessPiece)x).getColor() == color)
						   .collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] mat = p.PossibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					if (mat[i][j]){
						// DOWNCASTING
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						// undo
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}//if
					}//if
				}//for
			}//for
		}// for
		return true;
	}// testCheckMate

	// method initialSetup()
	private void initialSetup() {
		// color white
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		/*
		 * placeNewPiece('e', 2, new Rook(board, Color.WHITE)); placeNewPiece('e', 1,
		 * new Rook(board, Color.WHITE)); placeNewPiece('d', 1, new Rook(board,
		 * Color.WHITE));
		 */
		// color black
		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));
		/*
		 * placeNewPiece('d', 7, new Rook(board, Color.BLACK)); placeNewPiece('e', 7,
		 * new Rook(board, Color.BLACK)); placeNewPiece('e', 8, new Rook(board,
		 * Color.BLACK)); placeNewPiece('d', 8, new Rook(board, Color.BLACK));
		 */
	}
}
