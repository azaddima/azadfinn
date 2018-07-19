import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class MyCircle extends MyFormTemplate implements Serializable {

    int height;
    int width;

    Ellipse2D ellipse2D;

    public MyCircle(int x, int y, int height, int width){
        super(x, y);
        this.height = height;
        this.width = width;

        ellipse2D = new Ellipse2D.Double(x, y, width, height);
    }

    public boolean clickedInside(int mouseX, int mouseY) {

        int xPos = getX();
        int yPos = getY();



        return false;
    }

    void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        //g.setColor(getC());
        //g.fillOval(getX(), getY(), height, width);

        g2.setColor(getC());
        g2.fill(ellipse2D);

        // TODO: 19.07.2018 add draw? in settings
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

    public void redrawEllipse(){
        ellipse2D = new Ellipse2D.Double(getX(), getY(), width, height);
    }

    public Ellipse2D getEllipse2D(){
        return ellipse2D;
    }

}
