
public class Square {
	
	private int letter;
	private int number;
	private Piece piece;
	
	public Square(int letter, int number) {
		this.letter = letter;
		this.number = number;
		this.piece = null;
	}
	
	/** Returns this square's letter index on the table */
	public int getLetter() {
		return this.letter;
	}
	
	/** Returns this square's number index on the table */
	public int getNumber() {
		return this.number;
	}
	
	/** Returns the piece that sits on this square */
	public Piece getPiece() {
		return this.piece;
	}
	
	/** Sets a piece on this square */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public String getCoords() {
		return Letter.toString(this.letter) + this.number;
	}
	
	/** Returns a string interpretation of the piece on this square */
	public String toString() {
		if ( this.piece == null ) return "â�¤"; // â�¤
		
		// Return white pieces
		if ( this.piece.getColor() == PieceColor.WHITE) {
				 if(this.piece instanceof Pawn)		return "â™™";
			else if(this.piece instanceof Bishop)	return "â™—";
			else if(this.piece instanceof Knight)	return "â™˜";
			else if(this.piece instanceof Rook)		return "â™–";
			else if(this.piece instanceof King)		return "â™”";
			else if(this.piece instanceof Queen)	return "â™•";
		}
		
		// Return black pieces
			 	  if( this.piece instanceof Pawn )	return "â™Ÿ";
			 else if( this.piece instanceof Bishop )return "â™�";
			 else if( this.piece instanceof Knight )return "â™ž";
			 else if( this.piece instanceof Rook )	return "â™œ";
			 else if( this.piece instanceof King )	return "â™š";
			 else if( this.piece instanceof Queen )	return "â™›";
		
		// Program never reaches this far.
		return "";
	}
}