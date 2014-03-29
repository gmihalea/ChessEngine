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
		Move move = Game.getHistory().peek();
		PieceSet set = pawn.getColor() == PieceColor.WHITE ?  
				Board.getWhiteSet() : Board.getBlackSet();
		if(PieceType.getType(move.getEndSquare().getPiece()) == PieceType.PAWN && 
				Math.abs(move.getEndSquare().getNumber() - move.getStartSquare().getNumber()) == 2)
			set.capturePiece(move.getEndSquare().getPiece());
		//Game.makeMove()
		
			
	}
	
	public static void outOfCheck(){
		//incearca sa mute intr-un captureFreeSquare
		//altfel incearca sa blocheze cu alta piesa
	}
}