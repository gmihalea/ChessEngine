import java.util.ArrayList;
import java.util.Random;

public class SpecialMoves {
	
	public static void pawnPromotion(Pawn pawn, char c){
	
		Square intermediate = pawn.getPosition();
		PieceSet set = pawn.getColor() == PieceColor.WHITE ?  
				Board.getWhiteSet() : Board.getBlackSet();
		set.capturePiece(pawn);
		Piece piece;
		
		switch (c) {
        case 'r': piece = new Rook(intermediate, set.getColor());
                 break;
        case 'k': piece = new Knight(intermediate, set.getColor());
                 break;
        case 'b': piece = new Bishop(intermediate, set.getColor());
                 break;
        default:  piece = new Queen(intermediate, set.getColor());
		}
		
		set.addPiece(piece);
	}
	
	public static void smallCastling(){
		
	}

	public static void bigCastling() {
		
	}
	
	public static String outOfCheck(){
		Piece auxKing = Game.getMySet().getAvailablePieces().get(0);
		ArrayList<Square> captureFreeSquares = auxKing.getCaptureFreeSquares();
		int opponentColor = auxKing.getColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
		
		if(captureFreeSquares.size() != 0) {
			Move move;
			Random randGen = new Random();
			int m = randGen.nextInt(captureFreeSquares.size());
			move = new Move(auxKing.getPosition(),captureFreeSquares.get(m));
			Game.makeMove(move);
			return move.toString()+"\n";
		}
		else {
			Square s = Piece.canBeCaptured(auxKing, auxKing.getPosition(), opponentColor);
			
			for(Piece p : Game.getMySet().getAvailablePieces()) {
				for(Square sqr : p.getValidSquares())
					if(sqr.compareTo(s) == 0) {
						Move move = new Move(p.getPosition(), s);
						Game.makeMove(move);
						return move.toString() + "\n";
					}
			}
			
			return "resign";
		}
	}
}