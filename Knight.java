import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(Square position, int color) {
		super(position, color);
	}

	@Override
	public ArrayList<Square> getAvailableSquares() {
		return null;
	}

	@Override
	public ArrayList<Square> getAtackSquares() {
		return null;
	}

	@Override
	public ArrayList<Square> getCaptureFreeSquares() {
		return null;
	}

	@Override
	public boolean canBeCaptured() {
		return false;
	}
}