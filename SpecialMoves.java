import java.util.ArrayList;
import java.util.Random;

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
	
	public static String outOfCheck(){
		Piece auxKing = Game.getMySet().getAvailablePieces().get(0);
		ArrayList<Square> captureFreeSquares = auxKing.getCaptureFreeSquares();
		if(captureFreeSquares.size()!=0){
			Move move;
			Random randGen = new Random();
			int m = randGen.nextInt(captureFreeSquares.size());
			move = new Move(auxKing.getPosition(),captureFreeSquares.get(m));
			Game.makeMove(move);
			return move.toString()+"\n";
		} else {
		//altfel incearca sa blocheze cu alta piesa
		return "resign";
		}
	}
}