public class Letter {
	
	public static final int A = 1;
	public static final int B = 2;
	public static final int C = 3;
	public static final int D = 4;
	public static final int E = 5;
	public static final int F = 6;
	public static final int G = 7;
	public static final int H = 8;

	public static String toString(int letter) {
		switch(letter) {
			case Letter.A: return "A";
			case Letter.B: return "B";
			case Letter.C: return "C";
			case Letter.D: return "D";
			case Letter.E: return "E";
			case Letter.F: return "F";
			case Letter.G: return "G";
			default: return "H";
		}
	}
}