import java.util.ArrayList;

public abstract class Piece {
	
	private Square position = null;
	private int color = PieceColor.UNKNOWN;
	
	public Piece(Square position, int color) {
		this.position = position;
		this.color = color;
	}
	
	public abstract ArrayList<Square> getAvailableSquares();
	public abstract ArrayList<Square> getAtackSquares();
	public abstract ArrayList<Square> getCaptureFreeSquares();
	public abstract boolean canBeCaptured();
	
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