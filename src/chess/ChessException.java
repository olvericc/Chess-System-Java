package chess;

import boardgame.BoardException;

public class ChessException extends BoardException{

	// serial version UID
	private static final long serialVersionUID = 1L;

	// constructor with arguments
	public ChessException(String msg) {
		super(msg);
	}	
}
