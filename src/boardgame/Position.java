package boardgame;

public class Position {

	// class attributes 
	private Integer row;
	private Integer column;
	
	// pattern builder
	public Position() {}

	// constructor with arguments
	public Position(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}

	// getters and setters
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	
	// method toString
	@Override
	public String toString() {
		return row + ", " + column;
	}
	
}
