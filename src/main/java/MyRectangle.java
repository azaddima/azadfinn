import java.awt.Graphics;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
//import java.awt.Rectangle;

public class MyRectangle extends MyFormTemplate implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	private Graphics g;
	private int height;
	private int width;
	
	//Observer	
	public MyRectangle(int x, int y, int height, int width) {
		super(x, y);
		this.height = height;
		this.width = width;
		
	}


	public boolean clickedInside(int mouseX, int mouseY) {
		
		int xPos = getX();
		int yPos = getY();
		
		if(mouseX >= xPos && mouseY >= yPos) {
			if(mouseX <= (xPos + width) && mouseY <= (yPos + height) ){
				return true;
				
			}
		}
		
		return false;
	}
	
	public void moveTo(int x, int y){
		setX(x);
		setY(y);

		super.getObserver().notifyObservers();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	void draw(Graphics g) {
		
		g.setColor(getC());
		g.fillRect(getX(), getY(), width, height);
			
	}
	

}
