
public class Move {
	
	private Square startSquare;
	private Square endSquare;
	
	/** Constructor that gets a string */
	public Move(String moveString) {
		if ( moveString.length() < 4 ) {
			this.startSquare = null;
			this.endSquare = null;
		}
		else {
			this.startSquare = Board.getFromString(moveString.substring(0,2));
			this.endSquare = Board.getFromString(moveString.substring(2,4));
		}
	}
	
	/** Constructor that gets start and end square */
	public Move(Square startSquare, Square endSquare) {
		this.startSquare = startSquare;
		this.endSquare = endSquare;
	}

	public Square getStartSquare() {
		return this.startSquare;
	}
	
	public Square getEndSquare() {
		return this.endSquare;
	}
	
	@Override
	public String toString() {
		return (startSquare.getCoords() + endSquare.getCoords()).toLowerCase();
	}
}