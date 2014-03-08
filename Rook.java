import java.util.ArrayList;

public class Rook extends Piece {
	
	private boolean moved;

	public boolean hasMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public Rook(Square position, int color) {
		super(position, color);
	}

	@Override
	public ArrayList<Square> getValidSquares() {
		int row = this.getPosition().getNumber();
		int column = this.getPosition().getLetter();
		int oponentColor = (this.getColor() == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE; 
		Square intermediate;
		ArrayList<Square> result = new ArrayList<Square>();
		
		/*
		 * Checks availability upwards
		 */
		
		for(int i=row; i<8; i++) {
			intermediate = Board.translate(column, i+1);
			if(intermediate.getPiece() == null) {
				result.add(intermediate);
			} else if (intermediate.getPiece().getColor() == oponentColor) {
				result.add(intermediate);
				break;
			} else
				break;
		}
		
		/*
		 * Checks availability downwards
		 */
		
		for(int i=row; i>1; i--) {
			intermediate = Board.translate(column, i-1);
			if(intermediate.getPiece() == null) {
				result.add(intermediate);
			} else if (intermediate.getPiece().getColor() == oponentColor){
				result.add(intermediate);
				break;
			} else
				break;
		}
		
		/*
		 * Checks availability sideways to left
		 */
		
		for(int i=column; i>Board.A; i--) {
			intermediate = Board.translate(i-1, row);
			if(intermediate.getPiece() == null) {
				result.add(intermediate);
			} else if (intermediate.getPiece().getColor() == oponentColor){
				result.add(intermediate);
				break;
			} else
				break;
		}
		
		/*
		 * Checks availability sideways to right
		 */
		
		for(int i=column; i<Board.H; i++) {
			intermediate = Board.translate(i+1, row);
			if(intermediate.getPiece() == null) {
				result.add(intermediate);
			} else if (intermediate.getPiece().getColor() == oponentColor){
				result.add(intermediate);
				break;
			} else
				break;
		}
		
		return result;
	}

}