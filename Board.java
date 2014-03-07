
public class Board {
	
	public static final int A = 1;
	public static final int B = 2;
	public static final int C = 3;
	public static final int D = 4;
	public static final int E = 5;
	public static final int F = 6;
	public static final int G = 7;
	public static final int H = 8;
	
	private Square[][] board = null;
	private PieceSet blackSet = null;
	private PieceSet whiteSet = null;
	
	public Board() {
		this.board = new Square[9][9];
		this.blackSet = new PieceSet();
		this.whiteSet = new PieceSet();
	}
	
	private void initializeBoard() {
		for(int row = A; row <= H; row++) {
			for(int column = 1; column <= 8; column++) {
				this.board[row][column] = new Square(row, column);
			}
		}
	}
	
	private void placePiece(Piece piece, Square square) {
		piece.setPosition(square);
		square.setPiece(piece);
	}
	
	private void placeSets() {
		Piece piece;
		
		for(int i = A; i <= H; i++) {
			piece = new Pawn(this.board[2][i], PieceColor.BLACK);
			this.placePiece(piece, this.board[2][i]);
		}
	}
	
	public Square[][] getBoard() {
		return this.board;
	}
	
	public void setBoard(Square[][] board) {
		this.board = board;
	}
	
	public PieceSet getBlackSet() {
		return this.blackSet;
	}
	
	public PieceSet getWhiteSet() {
		return this.whiteSet;
	}
}