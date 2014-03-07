import java.util.ArrayList;

public class PieceSet {
	
	private ArrayList<Piece> available = null;
	private ArrayList<Piece> captured = null;
	
	public PieceSet(int color) {
		this.available = new ArrayList<Piece>(16);
		this.captured = new ArrayList<Piece>(16);
	}
	
	public ArrayList<Piece> getAvailablePieces() {
		return this.available;
	}
	
	public ArrayList<Piece> getCapturedPieces() {
		return this.captured;
	}
	
	public void capturePiece(Piece piece) {
		for(int i = 0; i < this.available.size(); i++)
			if(this.available.get(i) == piece) {
				this.captured.add(this.available.remove(i));
				break;
			}
	}
}