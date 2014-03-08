
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
	
	public static void initialize() {
		Board.board = new Square[9][9];
		Board.blackSet = new PieceSet(PieceColor.BLACK);
		Board.whiteSet = new PieceSet(PieceColor.WHITE);
		
		for(int row = A; row <= H; row++) {
			for(int column = 1; column <= 8; column++) {
				Board.board[row][column] = new Square(row, column);
			}
		}
	}
	
	public static Square translate(int letter, int number) {
		return Board.board[9 - number][letter];
	}
	
	private static void placePiece(Piece piece, Square square) {
		piece.setPosition(square);
		square.setPiece(piece);
	}
	
	private static void placeSets() {
		Piece piece;
		
		for(int i = A; i <= H; i++) {
			piece = new Pawn(Board.board[2][i], PieceColor.BLACK);
			Board.placePiece(piece, Board.board[2][i]);
		}
	}
	
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
}