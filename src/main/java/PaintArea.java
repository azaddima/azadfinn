import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class PaintArea extends JPanel implements Observer, Serializable{
	
	//My List of shapes. Will be changed later to hold any <Object>
	private ArrayList<MyRectangle> forms = new ArrayList<MyRectangle>();
	
	//Determines what to add to canvas.
	//Could work with booleans here, but will work with this for now.
	private int paintState = 0; // 1[Rectangle], 2[Circle]
	private int activeLayer = -1;
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
        for(MyRectangle rectangle: forms) {
        	rectangle.draw(g); //repaint() in draw method
        }
	}
	
	public void addRect(int x, int y) {
		forms.add(new MyRectangle(x,y, 50,50));
	}
	
	public MyRectangle getActiveRect() {
		if(activeLayer != -1) {
			return forms.get(activeLayer);
		}
		
		return null;
	}
	
	//returns paintState
	public void setPaintState(int paintState) {
		this.paintState = paintState;
	}
	
	public void resetPaintState() {
		this.paintState = 0;
	}
	
	public int getPaintState() {
		return paintState;
	}
	
	public void setActiveLayer(int activeLayer) {
		this.activeLayer = activeLayer;
	}
	
	public int getActiveLayer() {
		return activeLayer;
	}
	
	
	public void objectsInPos(int x, int y) {

		int highestLayer = -1;

		for(MyRectangle form: forms) {
			
			if(form.clickedInside(x, y)) {
				int layer = forms.indexOf(form);
				System.out.println("Layer of clicked Object: " + layer);

				if(layer > highestLayer){
					highestLayer = layer;
				}
				setActiveLayer(layer);
				
			}
		}
		
		System.out.println("Highest Layer: " + highestLayer);
	}
	
	public ArrayList<MyRectangle> getForms() {
		return forms;
	}

	public MyRectangle returnLast() {

		MyRectangle rectangle = forms.get(forms.size() -1);
		System.out.println("RETURN LAST OBJECT");

		return rectangle;
	}


	public void setForms(ArrayList<MyRectangle> forms) {
		this.forms = forms;
	}
	
	public void clearForms() {
		forms.clear();
		repaint();
	}


	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		
	}

	

	
}
