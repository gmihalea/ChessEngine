import java.util.ArrayList;

public class Pawn extends Piece {
	
	private boolean moved;

	public boolean hasMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public Pawn(Square position, int color) {
		super(position, color);
	}

	@Override
	public ArrayList<Square> getValidSquares() {
		// TODO Auto-generated method stub
		return null;
	}

}