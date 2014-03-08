import java.util.ArrayList;

public abstract class Piece {
	
	private Square position;
	private int color;
	
	public Piece(Square position, int color) {
		this.position = position;
		this.color = color;
	}
	
	public abstract ArrayList<Square> getValidSquares();
	
	public ArrayList<Square> getAtackSquares() {
	
		return null;
	}
	
	public ArrayList<Square> getCaptureFreeSquares() {
		
		return null;
	}

	public static boolean canBeCaptured(Square square) {
	
		return false;
	}
	
	public Square getPosition() {
		return this.position;
	}
	
	public void setPosition(Square position) {
		this.position = position;
	}
	
	public int getColor() {
		return this.color;
	}
}