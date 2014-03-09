
public class GameStatus {

	private static boolean blackCheck;
	private static boolean blackCheckMate;
	private static boolean blackStaleMate;
	private static boolean whiteCheck;
	private static boolean whiteCheckMate;
	private static boolean whiteStaleMate;
	
	public static boolean isBlackCheck() {
		return GameStatus.blackCheck;
	}

	public static void setBlackCheck(boolean blackCheck) {
		GameStatus.blackCheck = blackCheck;
	}

	public static boolean isBlackCheckMate() {
		return GameStatus.blackCheckMate;
	}

	public static void setBlackCheckMate(boolean blackCheckMate) {
		GameStatus.blackCheckMate = blackCheckMate;
	}

	public static boolean isBlackStaleMate() {
		return GameStatus.blackStaleMate;
	}

	public static void setBlackStaleMate(boolean blackStaleMate) {
		GameStatus.blackStaleMate = blackStaleMate;
	}

	public static boolean isWhiteCheck() {
		return GameStatus.whiteCheck;
	}

	public static void setWhiteCheck(boolean whiteCheck) {
		GameStatus.whiteCheck = whiteCheck;
	}

	public static boolean isWhiteCheckMate() {
		return GameStatus.whiteCheckMate;
	}

	public static void setWhiteCheckMate(boolean whiteCheckMate) {
		GameStatus.whiteCheckMate = whiteCheckMate;
	}

	public static boolean isWhiteStaleMate() {
		return GameStatus.whiteStaleMate;
	}

	public static void setWhiteStaleMate(boolean whiteStaleMate) {
		GameStatus.whiteStaleMate = whiteStaleMate;
	}
	
	public static boolean isAnyChecked() {
			return GameStatus.blackCheck || GameStatus.whiteCheck;
	}
	
	public static boolean isAnyCheckMated() {
		return GameStatus.blackCheckMate || GameStatus.whiteCheckMate;
	}
	
	public static boolean isAnyStaleMated() {
		return GameStatus.blackStaleMate || GameStatus.whiteStaleMate;
	}
	
	
}
