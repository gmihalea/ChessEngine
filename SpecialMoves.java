public class SpecialMoves {
	
	public static void pawnPromotion(Pawn pawn){
	
		Square intermediate = pawn.getPosition();
		PieceSet set = pawn.getColor() == PieceColor.WHITE ?  
				Board.getWhiteSet() : Board.getBlackSet();
		set.capturePiece(pawn);
		Piece piece = new Queen(intermediate, set.getColor());
		set.addPiece(piece);

	}
	
	public static void smallCastling(){
		
	}

	public static void bigCastling() {
		
	}
	
	public static void enPassant(Pawn pawn){
		
	}
	
	public static void outOfCheck(){
		//incearca sa mute intr-un captureFreeSquare
		//altfel incearca sa blocheze cu alta piesa
	}
}