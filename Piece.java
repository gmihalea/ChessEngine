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
		int opponentColor = (this.color == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
		
		for(Square square : validSquares)
			if(!Piece.canBeCaptured(square, opponentColor))
				result.add(square);
		
		return result;
	}
	
	private static boolean checkDirection(Square square, int letter, int number, int opponentColor, int... opponents) {
		int pieceType;
		Square intermediate;
		
		intermediate = square;
		while(Board.isSquareValid(intermediate.getLetter() + letter, intermediate.getNumber() + number)) {
			intermediate = Board.translate(intermediate.getLetter() + letter, intermediate.getNumber() + number);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					for(int opponent : opponents) {
						if(pieceType == opponent)
							return true;
						break;
					}
				}
				else break;
			}
		}
		
		return false;
	}

	public static boolean canBeCaptured(Square square, int opponentColor) {
		int number = square.getNumber();
		int letter = square.getLetter();
		Square intermediate;
		
		//TODO: adauga pion
		for(int i = -1; i <= 1; i++)
			for(int j = -1; j <= 1; j++)
				if(!(i == 0 && j == 0)) {
					if(Board.isSquareValid(letter + i, number + j)){
						intermediate = Board.translate(letter + i, number + j);
						if(intermediate.getPiece() != null) {
							if(intermediate.getPiece().getColor() == opponentColor) {
								if(PieceType.getType(intermediate.getPiece()) == PieceType.KING)
									return true;
								break;
							}
							else break;
						}
					}
					
					if(i != j) {
						if(Piece.checkDirection(square, i, j, opponentColor, PieceType.QUEEN, PieceType.BISHOP))
							return true;
					}
					else {
						if(Piece.checkDirection(square, i, j, opponentColor, PieceType.QUEEN, PieceType.ROOK))
							return true;
					}
				}
		
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				if(Math.abs(i) != Math.abs(j) && i != 0 && j != 0) {
					if(Board.isSquareValid(letter + i, number + j)) {
						intermediate = Board.translate(letter + i, number + j);
						if(intermediate.getPiece() != null
								&& intermediate.getPiece().getColor() == opponentColor
								&& PieceType.getType(intermediate.getPiece()) == PieceType.KNIGHT)
							return true;
					}
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