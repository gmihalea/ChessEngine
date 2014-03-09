import java.util.ArrayList;

public class King extends Piece {
	
	private boolean moved;

	public boolean hasMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public King(Square position, int color) {
		super(position, color);
	}

	@Override
	public ArrayList<Square> getValidSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		int opponentColor = (this.getColor() == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
		int row = this.getPosition().getNumber();
		int column = this.getPosition().getLetter();
		Square intermediate;
		
		for( int i = -1; i <= 1; i++)
			for( int j = -1; j <= 1; j++)
				if(!(i == 0 && j == 0)) {
					if(Board.isSquareValid(column + i, row + j)){
						intermediate = Board.translate(column + i, row + j);
						if(intermediate.getPiece() == null || intermediate.getPiece().getColor() == opponentColor)
							result.add(intermediate);
					}	
				}
		
		return result;
	}

	
}