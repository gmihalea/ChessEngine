
public class Board {
	
	public static final int A = 1;
	public static final int B = 2;
	public static final int C = 3;
	public static final int D = 4;
	public static final int E = 5;
	public static final int F = 6;
	public static final int G = 7;
	public static final int H = 8;
	
	private static Square[][] board = null;
	private static PieceSet blackSet = null;
	private static PieceSet whiteSet = null;
	
	/**Initializez the chess table: A 9x9 matrix of squares
	 * <br> Line 1 and column 1 are unused, so they remain set to null
	 * <br> All the other squares of the matrix are initialized as emty squares.
	 * 
	 * Se initializeaza cele 2 seturi de piese (se aseaza pe tabla)
	 */
	public static void initialize() {
		Board.board = new Square[9][9];
		
		for(int number = 1; number <= 8; number++ )
			for(int letter = A; letter <= H; letter++)
				Board.board[number][letter] = new Square( 9 - number , letter );
		Board.placePieces();
	}
	
	private static void placePieces() {
		Board.blackSet = new PieceSet(PieceColor.BLACK);
		Board.whiteSet = new PieceSet(PieceColor.WHITE);
	}
	
	/**Gettting a letter and a number (int), it returns the square at the
	 * <br> specified indexation.
	 * @param letter
	 * @param number
	 * @return Square at [ letter ] [ number ]
	 */
	public static Square translate(int letter, int number) {
		return Board.board[9 - number][letter];
	}
	
	/**
	 * 
	 * @return
	 */
	public static Square[][] get() {
		return Board.board;
	}
	
	public static void set(Square[][] board) {
		Board.board = board;
	}
	
	public static PieceSet getBlackSet() {
		return Board.blackSet;
	}
	
	public static PieceSet getWhiteSet() {
		return Board.whiteSet;
	}
	
	public static boolean isSquareValid(int letter, int number) {
		return ( letter > 0 && letter < 9 && number > 0 && number < 9);
	}
	
	public static String printTable() {
		
		StringBuilder sb = new StringBuilder();
		
		for ( int i = 1 ; i <= 8 ; i++ ) {
			for ( int j = 1 ; j <= 8 ; j++ )
				sb.append(" " + Board.get()[i][j].toString() +" ");
				sb.append("\n");
			}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	

	
}