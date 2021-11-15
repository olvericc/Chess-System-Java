package boardgame;

public class BoardException extends RuntimeException{

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// constructor with arguments
	public BoardException(String msg) {
		super(msg);
	}
}
