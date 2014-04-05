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
		
		if ( isKingsideCastlingPossible()  ) result.add(Board.translate(column + 2, row));
		if ( isQueensideCastlingPossible() ) result.add(Board.translate(column - 2, row));
		
		return result;
	}

	/*If both 2 squares to the right are clear and the rook on the third hasn't moved,
	 * castling is possible */
	private boolean isKingsideCastlingPossible() {
		return (													// Checking if: 
				(this.moved == false) 									// King hasn't moved
				&& (Piece.canBeCaptured(this, this.getPosition(), this.getColor() == PieceColor.BLACK
							? PieceColor.WHITE : PieceColor.BLACK		//   and is not checked
					) == null)									
				&& (Board.translate(this.getPosition().getLetter()+1,	// First square to the right 
							this.getPosition().getNumber())				//   is clear
					.getPiece() == null)
				&& (Board.translate(this.getPosition().getLetter()+2,	// 2nd square to the right
							this.getPosition().getNumber())				//   is clear
					.getPiece() == null)
				&& (PieceType.getType( Board.translate(
							this.getPosition().getLetter()+3,			// 3rd square to the right
							this.getPosition().getNumber())				//   has a rook on it
					.getPiece()) == PieceType.ROOK)
				&& (((Rook) (Board.translate(this.getPosition().getLetter()+3,
						   this.getPosition().getNumber())				//   that hasn't moved yet
				.getPiece())).hasMoved() == false)
				&& Piece.canBeCaptured(null, Board.translate(			// Moving will not get me
											this.getPosition().getLetter()+2, //   checked
											this.getPosition().getNumber()
											),
							this.getColor() == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK				
					) == null
				);
	}
	
	private boolean isQueensideCastlingPossible() {
		
	//Checking if:
		// The king hasn't moved
		if ( this.moved == true ) return false;
		// The king isn't checked
		if ( Piece.canBeCaptured(this, this.getPosition(), this.getColor() == PieceColor.BLACK ?
						PieceColor.WHITE : PieceColor.BLACK) != null )
			return false;
		
		// None of the squares from the king to destination are attackable
		//    nor do they have pieces on
		for ( int i = Letter.D ; i > Letter.A ; i++ ) {
			Square sq = Board.translate(i, this.getPosition().getNumber());
			if ( Piece.canBeCaptured(sq.getPiece(), sq, this.getColor() == PieceColor.BLACK ?
					PieceColor.WHITE : PieceColor.BLACK ) != null )
				return false;
		}
		
		return (													// Checking if
				&& (Board.translate(this.getPosition().getLetter()-1,	// First square to the left 
							this.getPosition().getNumber())				//   is clear
					.getPiece() == null)
				&& (Board.translate(this.getPosition().getLetter()-2,	// 2nd square to the left
							this.getPosition().getNumber())				//   is clear
					.getPiece() == null)
				&& (Board.translate(this.getPosition().getLetter()-3,	// 3rd square to the left
							this.getPosition().getNumber())				//   is clear
					.getPiece() == null)
				&& (PieceType.getType( Board.translate(
							this.getPosition().getLetter()-4,			// 4th square to the left
							this.getPosition().getNumber())				//   has a rook on it
					.getPiece()) == PieceType.ROOK)
				&& (((Rook) (Board.translate(this.getPosition().getLetter()-4,
						   this.getPosition().getNumber())				//   that hasn't moved yet
				.getPiece())).hasMoved() == false)
				&& Piece.canBeCaptured(null, Board.translate(			// Moving will not get me
											this.getPosition().getLetter()-2, //   checked
											this.getPosition().getNumber()
											),
							this.getColor() == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK				
					) == null
				);
	}
}