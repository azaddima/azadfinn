import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class MyCircle extends MyFormTemplate implements Serializable {

    /*
    int height;
    int width;
    */

    Ellipse2D ellipse2D;

    public MyCircle(int x, int y, int height, int width){
        super(x, y);
        setWidth(width);
        setHeight(height);

        setWidhtSave(width);
        setHeightSave(height);


        ellipse2D = new Ellipse2D.Double(x, y, width, height);
    }


    void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        //g.setColor(getC());
        //g.fillOval(getX(), getY(), height, width);

        g2.setColor(getC());
        g2.fill(ellipse2D);


    }


    public void redrawEllipse(){
        ellipse2D = new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    public Ellipse2D getEllipse2D(){
        return ellipse2D;
    }

}
