import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class PaintArea extends JPanel implements Observer, Serializable{
	
	//My List of shapes. Will be changed later to hold any <Object>
	private ArrayList<MyFormTemplate> forms = new ArrayList<MyFormTemplate>();
	
	//Determines what to add to canvas.
	//Could work with booleans here, but will work with this for now.
	private int paintState = 0; // 1[Rectangle], 2[Circle] -- ONLY FOR DRAWING


	private int activeLayer = -1;
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

        for(MyFormTemplate form: forms) {

        	//Draw Rectangle
        	if(form instanceof MyRectangle){
				((MyRectangle)form).draw(g); //repaint() in draw method
			}

			//Draw Circle
			if(form instanceof MyCircle){
        		//MyCircle handles drawing of shape
				((MyCircle) form).redrawEllipse();
        		((MyCircle) form).draw(g);
			}

        }
	}
	
	public void addRect(int x, int y) {
		forms.add(new MyRectangle(x,y, 50,50));
	}

	public void addCircle(int x, int y){
		forms.add(new MyCircle(x,y, 50,50));
	}

	public MyFormTemplate getActiveForm() {
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

		System.out.println(activeLayer + ":ActiveLayer");
	}
	
	public int getActiveLayer() {
		return activeLayer;
	}
	
	
	public void objectsInPos(int x, int y) {

		int highestLayer = -1;

		for(MyFormTemplate form: forms) {

			// If form is a rectangle
			if(form instanceof MyRectangle){

				if(((MyRectangle)form).clickedInside(x, y)) {
					int layer = forms.indexOf(form);
					System.out.println("Layer of clicked Object: " + layer);

					if(layer > highestLayer){
						highestLayer = layer;
					}
				}
			}


			// TODO: 19.07.2018 Is circle pressed?
			if(form instanceof MyCircle){
				if(((MyCircle) form).getEllipse2D().contains(x,y)){
					int layer = forms.indexOf(form);
					System.out.println("Layer of clicked Circle: " + layer);

					if(layer > highestLayer){
						highestLayer = layer;
					}
				}
			}


		}

		setActiveLayer(highestLayer);
		
		System.out.println("Highest Layer: " + highestLayer);
	}
	
	public ArrayList<MyFormTemplate> getForms() {
		return forms;
	}

	public MyFormTemplate returnLast() {

		MyFormTemplate form = forms.get(forms.size() -1);
		System.out.println("RETURN LAST OBJECT");

		return form;
	}


	public void setForms(ArrayList<MyFormTemplate> forms) {
		this.forms = forms;
	}
	
	public void clearForms() {
		forms.clear();
		repaint();
	}


	public void update(Observable o, Object arg) {

		
		
	}


}
