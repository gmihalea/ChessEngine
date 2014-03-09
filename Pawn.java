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
		ArrayList<Square> result = new ArrayList<Square>();
		int opponentColor = (this.getColor() == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
		int row = this.getPosition().getNumber();
		int column = this.getPosition().getLetter();
		Square intermediate1, intermediate2;
		
		//Daca nu a mai fost mutat si are doua casute goale deasupra
		intermediate1 = this.getColor() == PieceColor.WHITE ? Board.get()[column][row + 1] : 
			Board.get()[column][row - 1];
		intermediate2 = this.getColor() == PieceColor.WHITE ? Board.get()[column][row + 2] : 
			Board.get()[column][row - 2];
	
		if(!this.moved && intermediate1.getPiece() == null && intermediate2.getPiece() == null) {
				result.add(intermediate1);
				result.add(intermediate2);
			}
		else{
			//Merge inainte
			if(intermediate1.getPiece() == null )
					result.add(intermediate1);
		}
		
		//Daca e in extrema stanga cu albul - dreapta cu negrul
		intermediate1 = this.getColor() == PieceColor.WHITE ? Board.get()[Board.B][row + 1] : Board.get()[Board.B][row - 1];
		
		if(column == Board.A  &&
				intermediate1.getPiece().getColor() == opponentColor) {
				result.add(intermediate1);
		}
	
		//Daca e in extrema	dreapta cu albul - stanga cu negrul
		intermediate1 = this.getColor() == PieceColor.WHITE ? Board.get()[Board.G][row + 1] : Board.get()[Board.G][row - 1];
		
		if(column == Board.H &&
				intermediate1.getPiece().getColor() == opponentColor) {
				result.add(intermediate1);
		}
				
		//Daca e undeva intre extreme
		intermediate1 = this.getColor() == PieceColor.WHITE ? Board.get()[column + 1][row + 1] : Board.get()[column - 1][row - 1] ;
		
		if(intermediate1.getPiece().getColor() == opponentColor)
				result.add(intermediate1);
		
		intermediate1 = this.getColor() == PieceColor.WHITE ? Board.get()[column - 1][row + 1]: Board.get()[column + 1][row - 1] ;
		
		if(intermediate1.getPiece().getColor() == opponentColor )
				result.add(intermediate1);
		
		return result;
	}

}