import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class Game {
	
	private static int mode = GameMode.DEFAULT;
	private static long whiteClock;
	private static long blackClock;
	private static Stack<Move> history = new Stack<Move>();
	
	@SuppressWarnings("unused")
	private static long myClock;
	private static PieceSet mySet;
	private static PieceSet opponentSet;
	private static int turn;
	private static int myColor;
	private static int opponentColor;
	
	/** Creates a new game:
	 * <br> - Clears the table
	 * <br> - Sets new piece sets in the default positions
	 * <br> - Sets WHITE on turn
	 * <br> - Resets clocks
	 */
	public static void initialize() {
		Board.initialize();
		Game.whiteClock = Game.blackClock = Game.myClock = 0;
		Game.mySet = Board.getBlackSet();
		Game.opponentSet = Board.getWhiteSet();
		Game.turn = PieceColor.WHITE;
		Game.myClock = Game.blackClock;
		//TODO: set myColor, opponentColor
		Game.myColor = PieceColor.BLACK;
		Game.opponentColor = PieceColor.WHITE;
		//TODO: Check what set should I be
	}
	
	/** Switches turns */
	public static void changeTurn() {
		Game.turn = (Game.turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
	}
	
	
	private static String think(){
		if(Game.mySet.getColor()==PieceColor.BLACK && GameStatus.isBlackCheck() || 
		   Game.mySet.getColor()==PieceColor.WHITE && GameStatus.isWhiteCheck()	){
			 return SpecialMoves.outOfCheck();
		} else return getRandomMove();
	}
	
	
	
	/** Verifies if the move made by the opponent is valid:
	 * <br> - If the move is valid, it replies with another move.
	 * <br> - If the move is invalid, it returns a message, pointing it out.
	 * @param moveString opponent's move
	 * @return My move , OR error message
	 */
	public static String replyToMove(String moveString) {
		Move move = new Move ( moveString );
		// The move is valid ONLY IF :
		if (move.getStartSquare() == null ||				// it has valid start
			move.getEndSquare() == null ||					// and end positions,
			move.getStartSquare().getPiece() == null || 	// the piece on the start position has
			move.getStartSquare().getPiece().getColor() != Game.turn || // colour of the turn				
			move.getStartSquare().getPiece().getValidSquares().indexOf(move.getEndSquare()) < 0 )
				return "Illegal move: " + moveString;	//^^ and the end square is a valid move
		
		makeMove(move); // If WinBoard sent a valid move, the move is made
				
		if ( Game.mode == GameMode.FORCE) return "";	// We don't move when in FORCE mode
			
		return think(); // Must be changed with a method that returns a clever move.
	}
	
	/** Returns a random move (and changes the turn) */
	// It changes the turn because it calls the makeMove() method
	public static String getRandomMove() {
		Random randGen = new Random();
		Piece pieceToMove;
		
		int randomIndex;
		int tries = 100;
		do { randomIndex = randGen.nextInt(mySet.getAvailablePieces().size());
			pieceToMove = mySet.getAvailablePieces().get(randomIndex);
			tries--;
		} while ( pieceToMove.getValidSquares().size() == 0 && tries > 0);
		
		if ( tries ==0 ) return "resign";
		
		ArrayList<Square> possibleMoves = pieceToMove.getValidSquares();
		Move randMove = new Move (	pieceToMove.getPosition(),
									possibleMoves.get((randGen.nextInt(possibleMoves.size()))));
		
		return makeMove(randMove);
	}

	
	public static PieceSet getMySet() {
		return mySet;
	}

	/** Makes a move, and then changes the turn.
	 * @return the move in a string interpretation*/
	// We are SURE the move is VALID. It has been checked in earlier methods
	public static String makeMove(Move move) {
		if ( move.getEndSquare().getPiece() != null ) // If there is an ENEMY PIECE on END POSITION
			opponentSet.capturePiece(move.getEndSquare().getPiece()); // we CAPTURE it
			
		move.getStartSquare().getPiece().setPosition(move.getEndSquare()); // We move our piece
		move.getEndSquare().setPiece(move.getStartSquare().getPiece()); // on the end square
		move.getStartSquare().setPiece(null); // And remove it from the old square
		
		history.add(move);
		GameStatus.update(move.getStartSquare().getPiece().getColor());
		
		Game.changeTurn(); // The turn will only get changed AFTER I think my next move.
		return move.toString();
	}
	
	

	
	/*
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
		
		switch(PieceType.getType(move.getEndSquare().getPiece())) {
		
			case PieceType.PAWN:
				((Pawn)move.getEndSquare().getPiece()).setMoved(true);
				break;
				
			case PieceType.KING:
				((King)move.getEndSquare().getPiece()).setMoved(true);
				break;
				
			case PieceType.ROOK:
				((Rook)move.getEndSquare().getPiece()).setMoved(true);
				break;
		}
		
		Game.changeTurn();
	}
	
	//TODO: COD URAT !!! TREBUIE OPTIMIZAT
	public static String play(Move move) {
		String result="";
		
		if(move!=null) {
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
				do {int x = r.nextInt(mySet.getAvailablePieces().size());
					pieceToMove = mySet.getAvailablePieces().get(x);
				} while ( pieceToMove.getValidSquares().size() == 0 );
				
				ArrayList<Square> possibleMoves = pieceToMove.getValidSquares();
				Move randMove = new Move (
						pieceToMove.getPosition(), possibleMoves.get((r.nextInt(possibleMoves.size()))));
				makeMove(randMove);
				result = randMove.toString();
			}
		}
		
		} else {
			Random r = new Random();
			Piece pieceToMove;
			System.out.println(mySet.getAvailablePieces().get(1).getColor());
			do {int x = r.nextInt(mySet.getAvailablePieces().size());
				pieceToMove = mySet.getAvailablePieces().get(x);
			} while ( pieceToMove.getValidSquares().size() == 0 );
			
			ArrayList<Square> possibleMoves = pieceToMove.getValidSquares();
			Move randMove = new Move (
					pieceToMove.getPosition(), possibleMoves.get((r.nextInt(possibleMoves.size()))));
			makeMove(randMove);
			result = randMove.toString();
		}
		
		return result;
	}
	
	*/
		
		
	public static void setDefaultMode() {
		mode=GameMode.DEFAULT;
		mySet = turn == PieceColor.WHITE ? Board.getWhiteSet() : Board.getBlackSet();
		myClock = turn == PieceColor.WHITE ? Game.whiteClock : Game.blackClock;
	}
	
	public static void setForceMode() {
		mode=GameMode.FORCE;
	}
	
	public static void setTurn(int color) {
		Game.mySet = (color == PieceColor.WHITE) ? Board.getBlackSet() : Board.getWhiteSet();
		Game.myClock = (color == PieceColor.WHITE) ? Game.blackClock : Game.whiteClock;
	}
	
	
	
	
	public static boolean validMove(Move move){
		if(move.getStartSquare().getPiece() != null &&  move.getStartSquare().getPiece().getColor() == turn){
			ArrayList<Square> possibleMoves = move.getStartSquare().getPiece().getValidSquares();
			if(possibleMoves.contains(move.getEndSquare())){
				return true;
				}
			}
		
		return false;
	}
	
	public static  Stack<Move> getHistory() {
		return history;
	}
}