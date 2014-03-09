import java.util.ArrayList;
import java.util.Random;


public class Game {
	
	private static int mode = GameMode.DEFAULT;
	private static long whiteClock;
	private static long blackClock;
	private static long myClock;
	private static PieceSet mySet;
	private static int turn;
	
	public static void initialize() {
		Board.initialize();
		Game.whiteClock = Game.blackClock = Game.myClock = 0;
		Game.mySet = Board.getBlackSet();
	}
	
	public static void makeMove(Move move) {
		if(move.getEndSquare().getPiece() != null) {
			if(Game.mySet == Board.getWhiteSet())
				Board.getBlackSet().capturePiece(move.getEndSquare().getPiece());
			else
				Board.getWhiteSet().capturePiece(move.getEndSquare().getPiece());
		}
		
		move.getEndSquare().setPiece(move.getStartSquare().getPiece());
		move.getStartSquare().setPiece(null);
		move.getEndSquare().getPiece().setPosition(move.getEndSquare());
		
		Game.changeTurn();
	}
	
	public static void setDefaultMode() {
		mode=GameMode.DEFAULT;
	}
	
	public static void setForceMode() {
		mode=GameMode.FORCE;
	}
	
	public static void setTurn(int color) {
		Game.mySet = (color == PieceColor.WHITE) ? Board.getBlackSet() : Board.getWhiteSet();
		Game.myClock = (color == PieceColor.WHITE) ? Game.blackClock : Game.whiteClock;
	}
	
	public static void changeTurn() {
		Game.turn = (Game.turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
	}
	
	public static String play(Move move) {
		String result="";
		
		if (!validMove(move)){
			result = "Illegal move: " + move.toString();
			return result;
		} else {
			if(mode==GameMode.FORCE){
					result = "";
					makeMove(move);
			} else {
				makeMove(move);
				Random r = new Random();
				Piece pieceToMove;
				do {
					pieceToMove = mySet.getAvailablePieces().get(r.nextInt(mySet.getAvailablePieces().size()));
				} while ( pieceToMove.getValidSquares().size() != 0 );
				ArrayList<Square> possibleMoves = pieceToMove.getValidSquares();
				Move randMove = new Move (pieceToMove.getPosition(), possibleMoves.get((r.nextInt(possibleMoves.size()))));
				makeMove(randMove);
				result = randMove.toString();
			}
		}
		return result;
	}
	
	public static boolean validMove(Move move){
		if(move.getStartSquare().getPiece().getColor() == turn){
			ArrayList<Square> possibleMoves = move.getStartSquare().getPiece().getValidSquares();
			if(possibleMoves.contains(move.getEndSquare())){
					return true;
				}
			}
		
		return false;
	}
	
}