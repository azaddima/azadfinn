import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;

public class MyFormTemplate implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	ObservableForms o = new ObservableForms();
	
	private int x;
	private int y;
	private Color c;
	
	private int r = 0;
	private int g = 0;
	private int b = 0;
	

	public MyFormTemplate(int x, int y, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public MyFormTemplate(int x, int y) {
		this(x,y, new Color(0,0,0));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
	
	public void setC() {
		this.c = new Color(r,g,b);

		//Notify Observer for update in GUI(View)
		o.notifyObservers(c);
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;

	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
		
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	
	}
	
	public ObservableForms getObserver() {
		return o;
	}
	
}
