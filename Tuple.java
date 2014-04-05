
public class Tuple<X, Y> {
	
	private X dataX;
	private Y dataY;
	
	public Tuple(X dataX, Y dataY) {
		this.setDataX(dataX);
		this.setDataY(dataY);
	}
	
	public Tuple() {
		
	}

	public X getDataX() { return this.dataX; }
	public void setDataX(X dataX) { this.dataX = dataX; }

	public Y getDataY() { return this.dataY; }
	public void setDataY(Y dataY) { this.dataY = dataY; }
}
