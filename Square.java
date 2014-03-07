
public class Square {
	
	private int row;
	private int column;
	private Piece piece = null;
	
	public Square(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}