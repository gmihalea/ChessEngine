
public class Square {
	
	private int letter;
	private int number;
	private Piece piece;
	
	public Square(int letter, int number) {
		this.letter = letter;
		this.number = number;
	}
	
	public int getLetter() {
		return this.letter;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}