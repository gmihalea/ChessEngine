
public class Game {
	
	private static int mode = GameMode.DEFAULT;
	private static long whiteClock;
	private static long blackClock;
	private static long myClock;
	private static PieceSet mySet;
	
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
		
	}
	
	public static void setForceMode() {
		
	}
	
	public static void setTurn(int color) {
		Game.mySet = (color == PieceColor.WHITE) ? Board.getBlackSet() : Board.getWhiteSet();
		Game.myClock = (color == PieceColor.WHITE) ? Game.blackClock : Game.whiteClock;
	}
	
	public static void changeTurn() {
		Game.turn = (Game.turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
	}
	
	public static void play() {
		
	}
}