
public class Move {
	
	private Square startSquare;
	private Square endSquare;
	
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
		return startSquare.getCoords() + endSquare.getCoords();
	}
}