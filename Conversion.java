import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Conversion {

	public static void main (String[] args) throws Exception {


		// Pentru afisare in fisier
		Writer tablePrinter=null;
		FileWriter showWinBoardCommands = null;
		FileWriter showMyCommands = null;





		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Game.initialize();
		String result=null;
		String command=null;

		command = in.readLine();
		// Comenzi acceptate:
		// xboard, new, force, go, white, black, quit, resign, move
		do {
			command = in.readLine().toLowerCase();
			showWinBoardCommands = new FileWriter("commandsWB.log", true); showWinBoardCommands.write(command+"\n"); showWinBoardCommands.close();
			if ( command.contains("level")) continue;
			if ( command.contains("time")) continue;
			if ( command.contains("otim")) continue;
			if ( command.contains("accepted")) continue;
			if ( command.contains("quit")) return;



			switch(command) {
				case "protover 2":
				case "xboard":
				case "hard":
				case "random":
				case "post":
					// ajuta lafel de mult ca o drujba intr-o maternitate
					break;
				case "new":
					Game.initialize();
					/* Reset the board to the standard chess starting position.
					 * Set White on move. Leave force mode and set the engine to play Black.
					 * Associate the engine's clock with Black and the opponent's clock with White.
					 * Reset clocks and time controls to the start of a new game.
					 * Use wall clock for time measurement. Stop clocks.
					 * Do not ponder on this move, even if pondering is on.
					 * Remove any search depth limit previously set by the sd command.
					 */
					break;
				case "force":
					 Game.setForceMode();
					/* Set the engine to play neither color ("force mode").
					 * Stop clocks.
					 * The engine should check that moves received in force mode are legal and made
					 * in the proper turn, but should not think, ponder, or make moves of its own.
					 */
					break;
				case "go":
					Game.setDefaultMode();
					result = Game.getRandomMove();
					System.out.print(result);
					showMyCommands = new FileWriter("commandsENGINE.log", true); showMyCommands.write(result); showMyCommands.close();
					/* Leave force mode and set the engine to play the color that is on move.
					 * Associate the engine's clock with the color that is on move,
					 * the opponent's clock with the color that is not on move.
					 * Start the engine's clock. Start thinking and eventually make a move.
					 */
					break;
				case "white":
					/* Set White on move. Set the engine to play Black. Stop clocks. */
					break;
				case "black":
					/* Set Black on move. Set the engine to play White. Stop clocks. */
					break;
				case "resign":
					/* If your engine wants to resign, it can send the command "resign".
					 * Alternatively, it can use the "RESULT {comment}" command if the string
					 * "resign" is included in the comment; for example "0-1 {White resigns}".
					 * xboard relays the resignation to the user, the ICS, the other engine in
					 * Two Machines mode, and the PGN save file as required.
					 * Note that many interfaces work more smoothly if you resign before you move.
					 */
					break;
				default:
					result = Game.replyToMove(command);
					if (result!="") System.out.print(result);
					showMyCommands = new FileWriter("commandsENGINE.log", true); showMyCommands.write(result); showMyCommands.close();
					tablePrinter = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("BoardTest.out"), "UTF-8")); tablePrinter.write(Board.printTable()); tablePrinter.close();
					/* If the move is illegal, print an error message;
					 * see the section "Commands from the engine to xboard".
					 * If the move is legal and in turn, make it.
					 * If not in force mode, stop the opponent's clock, start the engine's clock,
					 * start thinking, and eventually make a move.
					 * MOVE EXAMPLES:
					 * e2e4		Normal moves
					 * e7e8q	Pawn promotion
					 * e1g1 e1c1 (1 or 8) Castling (Rocada)
					 */
			}// ends switch
		} while ( true );
	}
}