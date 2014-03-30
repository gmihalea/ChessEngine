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
	
	public static String outOfCheck() {
		
		Piece auxKing = Game.getMySet().getAvailablePieces().get(0);
		ArrayList<Square> captureFreeSquares = auxKing.getCaptureFreeSquares();
		int opponentColor = auxKing.getColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
		Square intermediate;
		int min, max;
		
		if(captureFreeSquares.size() != 0) {
			Move move;
			Random randGen = new Random();
			int m = randGen.nextInt(captureFreeSquares.size());
			move = new Move(auxKing.getPosition(), captureFreeSquares.get(m));
			Game.makeMove(move);
			return move.toString() + "\n";
		}
		else {
			Square s = Piece.canBeCaptured(auxKing, auxKing.getPosition(), opponentColor);
			
			for(Piece p : Game.getMySet().getAvailablePieces()) {
				for(Square sqr : p.getValidSquares())
					if(sqr == s) {
						Move move = new Move(p.getPosition(), s);
						Game.makeMove(move);
						return move.toString() + "\n";
					}
			}
			
			if(s.getLetter() != auxKing.getPosition().getLetter() && s.getNumber() != auxKing.getPosition().getNumber()) {
				min = Math.min(s.getLetter(), auxKing.getPosition().getLetter());
				max = Math.max(s.getLetter(), auxKing.getPosition().getLetter());
				int min1 = Math.min(s.getNumber(), auxKing.getPosition().getNumber());
				int max1 = Math.max(s.getNumber(), auxKing.getPosition().getNumber());
				
				for(int i = min; i <= max; i++) {
					if(Board.isSquareValid(min + i, min1 + i)) {
						intermediate = Board.translate(min + i, min1 + i);
						
						for(Piece p : Game.getMySet().getAvailablePieces()) {
							for(Square sqr : p.getValidSquares())
								if(sqr == intermediate) {
									Move move = new Move(p.getPosition(), intermediate);
									Game.makeMove(move);
									return move.toString() + "\n";
								}
						}
					}
				}
			}
			else {
				if(s.getLetter() != auxKing.getPosition().getLetter()) {
					min = Math.min(s.getLetter(), auxKing.getPosition().getLetter());
					max = Math.max(s.getLetter(), auxKing.getPosition().getLetter());
					
					for(int i = min; i <= max; i++) {
						if(Board.isSquareValid(min + i, s.getNumber())) {
							intermediate = Board.translate(min + i, s.getNumber());
							
							for(Piece p : Game.getMySet().getAvailablePieces()) {
								for(Square sqr : p.getValidSquares())
									if(sqr == intermediate) {
										Move move = new Move(p.getPosition(), intermediate);
										Game.makeMove(move);
										return move.toString() + "\n";
									}
							}
						}
					}
				}
				else {
					min = Math.min(s.getNumber(), auxKing.getPosition().getNumber());
					max = Math.max(s.getNumber(), auxKing.getPosition().getNumber());
					
					for(int i = min; i <= max; i++) {
						if(Board.isSquareValid(s.getLetter(), min + i)) {
							intermediate = Board.translate(s.getLetter(), min + i);
							
							for(Piece p : Game.getMySet().getAvailablePieces()) {
								for(Square sqr : p.getValidSquares())
									if(sqr == intermediate) {
										Move move = new Move(p.getPosition(), intermediate);
										Game.makeMove(move);
										return move.toString() + "\n";
									}
							}
						}
					}
				}
			}
			
			return "resign";
		}
	}
}