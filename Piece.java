import java.util.ArrayList;

public abstract class Piece {
	
	private Square position;
	private int color;
	
	public Piece(Square position, int color) {
		this.position = position;
		this.color = color;
		this.position.setPiece(this);
	}
	
	public abstract ArrayList<Square> getValidSquares();
	
	public ArrayList<Square> getAtackSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		ArrayList<Square> validSquares = this.getValidSquares();
		int opponentColor = (this.color == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
				
		for(Square square : validSquares)
			if(square.getPiece() != null && square.getPiece().getColor() == opponentColor)
				result.add(square);
		
		return result;
	}
	
	public ArrayList<Square> getCaptureFreeSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		ArrayList<Square> validSquares = this.getValidSquares();
		
		for(Square square : validSquares)
			if(!Piece.canBeCaptured(square))
				result.add(square);
		
		return result;
	}

	public static boolean canBeCaptured(Square square) {
		
		int number = square.getNumber();
		int letter = square.getLetter();
		int opponentColor = (square.getPiece().getColor() == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
		Square intermediate;
		
		for(int i = number; i < 8; i++) {
			intermediate = Board.translate(letter, i + 1);
			if(intermediate.getPiece() != null) {
				if(i == number + 1) {
					if(PieceType.getType(intermediate.getPiece()) == PieceType.KING)
						return true;
				}
			}
		}
	
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