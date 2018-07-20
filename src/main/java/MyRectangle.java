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

	
	//Observer	
	public MyRectangle(int x, int y, int height, int width) {
		super(x, y);

		setWidth(width);
		setHeight(height);
		setWidhtSave(width);
		setHeightSave(height);

	}


	public boolean clickedInside(int mouseX, int mouseY) {
		
		int xPos = getX();
		int yPos = getY();
		
		if(mouseX >= xPos && mouseY >= yPos) {
			if(mouseX <= (xPos + getWidth()) && mouseY <= (yPos + getHeight()) ){
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



	void draw(Graphics g) {
		
		g.setColor(getC());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
			
	}
	

}
