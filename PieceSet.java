import java.util.ArrayList;

public class PieceSet {
	
	private static final int NO_OF_PIECES = 16;
	
	private ArrayList<Piece> available = null;
	private ArrayList<Piece> captured = null;
	
	public PieceSet(int color) {
		int rowNumber = (color == PieceColor.WHITE) ? 2 : 7; 
		this.available = new ArrayList<Piece>(PieceSet.NO_OF_PIECES); 
		this.captured = new ArrayList<Piece>(PieceSet.NO_OF_PIECES);
		
		for (int letter = Letter.A; letter <= Letter.H; letter++)
			this.available.add(new Pawn(Board.translate(letter, rowNumber), color)); 
		
		rowNumber = (color == PieceColor.WHITE) ? 1 : 8;
		this.available.add(new Rook (Board.translate(Letter.A, rowNumber), color)); 	
		this.available.add(new Rook(Board.translate(Letter.H, rowNumber), color)); 
		this.available.add(new Bishop(Board.translate(Letter.B, rowNumber), color)); 
		this.available.add(new Bishop(Board.translate(Letter.G, rowNumber), color));
		this.available.add(new Knight(Board.translate(Letter.C, rowNumber), color)); 
		this.available.add(new Knight(Board.translate(Letter.F, rowNumber), color)); 
		this.available.add(new Queen(Board.translate(Letter.D, rowNumber), color)); 
		this.available.add(new King(Board.translate(Letter.E, rowNumber), color));
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