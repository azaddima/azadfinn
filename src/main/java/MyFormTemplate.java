import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;


public class MyFormTemplate implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	ObservableForms o = new ObservableForms();
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color c;

	private int widhtSave;
	private int heightSave;

	//Animation save
	private int xAnim;
	private int yAnim;
	
	private int r = 0;
	private int g = 0;
	private int b = 0;

	private int size;

	//--Animation
	private boolean isSinus;
	private boolean isRandomClr;

	//Sinus
	private int xSpeed;
	private int viewHeight;
	private int viewWidth;

	//Random color
	private int rSpeed;
	private int gSpeed;
	private int bSpeed;

	public MyFormTemplate(int x, int y, Color c) {
		this.x = x;
		this.y = y;
		this.c = c;
		size = 0;

		isSinus = false;
		isRandomClr = false;

	}
	
	public MyFormTemplate(int x, int y) {
		this(x,y, new Color(0, 0, 0));
	}

	public void setSize(int size){
		this.size = size;

		width = widhtSave + size;
		height = widhtSave + size;

		o.notifyObservers("width " + String.valueOf(width));
		o.notifyObservers("height "+ String.valueOf(height));

	}

	public void setSinusMovement(int xSpeed, int viewHeight, int viewWidth){

		this.xSpeed += xSpeed;
		this.viewHeight = viewHeight;
		this.viewWidth = viewWidth;

		timerSinus.start();
	}

	public void stopSinusMovement(){
		timerSinus.stop();
	}

	public void setRandomColorAnim(int rSpeed, int gSpeed, int bSpeed){
		this.rSpeed = rSpeed;
		this.gSpeed = gSpeed;
		this.bSpeed = bSpeed;

		timerRandomClr.start();


	}

	public void stopRanfomColorAnim(){
		timerRandomClr.stop();
		o.notifyObservers(this.c);
	}

	//TIMER MANAGEMENT
	Timer timerSinus = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			x += xSpeed;
			y = (int) ((viewHeight / 4) * Math.sin(x * 0.005f)) + (viewHeight / 2) - 150;

			if(x > viewWidth){
				x = 0;
			}
		}
	});

	Timer timerRandomClr = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int r = c.getRed();
			int g = c.getGreen();
			int b = c.getBlue();

			r += rSpeed;
			g += gSpeed;
			b += bSpeed;

			r %= 256;
			g %= 256;
			b %= 256;

			c = new Color(r,g,b);
		}
	});

	public int getxAnim() {
		return xAnim;
	}

	public void setxAnim(int xAnim) {
		this.xAnim = xAnim;
	}

	public int getyAnim() {
		return yAnim;
	}

	public void setyAnim(int yAnim) {
		this.yAnim = yAnim;
	}

	public boolean isSinus() {
		return isSinus;
	}

	public void setSinus(boolean sinus) {
		isSinus = sinus;
	}

	public boolean isRandomClr() {
		return isRandomClr;
	}

	public void setRandomClr(boolean randomClr) {
		isRandomClr = randomClr;
	}

	public int getSize(){
		return size;
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

	public int getWidth() {
		return width;
	}

	public void setWidhtSave(int widhtSave) {
		this.widhtSave = widhtSave;
	}

	public void setHeightSave(int heightSave) {
		this.heightSave = heightSave;
	}


	//Update width
	public void setWidth(int width) {
		this.width = width;
		widhtSave = width - size; // calculation for size modifaction. Original height should not be lost.

	}

	public int getHeight() {
		return height;
	}

	//Update height
	public void setHeight(int height) {
		this.height = height;


		heightSave = height - size; // calculation for size modifaction. Original height should not be lost.
	}

	public ObservableForms getObserver() {
		return o;
	}
	
}
