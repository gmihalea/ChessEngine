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

	public static boolean canBeCaptured(Square square, int opponentColor) {
		int number = square.getNumber();
		int letter = square.getLetter();
		int pieceType;
		Square intermediate;
		
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
				}
		
		for(int i = number; i < 8; i++) {
			intermediate = Board.translate(letter, i + 1);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK)
						return true;
					break;
				}
				else break;
			}
		}
		
		for(int i = number; i > 1; i--) {
			intermediate = Board.translate(letter, i - 1);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK)
						return true;
					break;
				}
				else break;
			}
		}
		
		for(int i = letter; i > Letter.A; i--) {
			intermediate = Board.translate(i - 1, number);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK)
						return true;
					break;
				}
				else break;
			}
		}
		
		for(int i = letter; i < Letter.H; i++) {
			intermediate = Board.translate(i + 1, number);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK)
						return true;
					break;
				}
				else break;
			}
		}
	
		for(int i = letter, j = number; i > Letter.A && j > 1; i--, j--) {
			intermediate = Board.translate(i - 1, j - 1);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(i == letter - 1 && j == number - 1
							&& pieceType == PieceType.PAWN && opponentColor == PieceColor.WHITE)
						return true;
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.BISHOP)
						return true;
					break;
				}
				else break;
			}
		}
		
		for(int i = letter, j = number; i > Letter.A && j < 8; i--, j++) {
			intermediate = Board.translate(i - 1, j + 1);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(i == letter - 1 && j == number + 1
							&& (pieceType == PieceType.PAWN && opponentColor == PieceColor.BLACK))
						return true;
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.BISHOP)
						return true;
					break;
				}
				else break;
			}
		}
		
		for(int i = letter, j = number; i < Letter.H && j < 8; i++, j++) {
			intermediate = Board.translate(i + 1, j + 1);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(i == letter + 1 && j == number + 1
							&& (pieceType == PieceType.PAWN && opponentColor == PieceColor.BLACK))
						return true;
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.BISHOP)
						return true;
					break;
				}
				else break;
			}
		}
		
		for(int i = letter, j = number; i < Letter.H && j > 1; i++, j--) {
			intermediate = Board.translate(i + 1, j - 1);
			if(intermediate.getPiece() != null) {
				if(intermediate.getPiece().getColor() == opponentColor) {
					pieceType = PieceType.getType(intermediate.getPiece());
					if(i == letter + 1 && j == number - 1
							&& (pieceType == PieceType.PAWN && opponentColor == PieceColor.WHITE))
						return true;
					if(pieceType == PieceType.QUEEN || pieceType == PieceType.BISHOP)
						return true;
					break;
				}
				else break;
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