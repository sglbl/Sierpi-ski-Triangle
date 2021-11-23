import java.awt.*;
import javax.swing.*;
import static java.lang.Math.*;

public class Drawer extends JFrame{
    /** GUI RELATED PRIVATES **/
    private static final long serialVersionUID = 1L;
    private PanelDrawer panel;

    // CONSTRUCTOR
    public Drawer() {
        super("Project by Suleyman Golbol!!"); // Title.
        panel = new PanelDrawer();
        this.add(panel);   //Adding panel to frame.
    }

}   //End of Drawer class.

class PanelDrawer extends JPanel{

    private static final long serialVersionUID = 1L;

    public void paintComponent(Graphics graphic){
        //Creating first point locations for a equilateral triangle 
        int length = 120;
        Point p1 = new Point(70 - length/2, (int)round(70 + length*sqrt(3.0)/2));
        Point p2 = new Point(70 + length/2, (int)round(70 + length*sqrt(3.0)/2));
        Point p3 = new Point(70, 70);

        //Creating graphic
        Graphics2D g2d = (Graphics2D) graphic.create();

        //Creating temporary points to paint it black.
        Point p1Temp = new Point(p1.getX(), p1.getY());
        Point p2Temp = new Point(p2.getX(), p2.getY());
        Point p3Temp = new Point(p3.getX(), p3.getY());

        int numberOfTimes = 5;
        for(int i=0; i<numberOfTimes; i++){
            //Firstly painting all triangles to black.
            blackFiller(p1Temp, p2Temp, p3Temp, g2d);                
            p1Temp.setX( p1Temp.getX() + length+10 );
            p2Temp.setX( p2Temp.getX() + length+10 );
            p3Temp.setX( p3Temp.getX() + length+10 );
        }
        //Drawing white triangle
        WhiteTriangle whiteTriangle = new WhiteTriangle(p1, p2, p3, g2d, numberOfTimes, length);
        g2d.draw( whiteTriangle );

    }
    
    public void blackFiller(Point p1, Point p2, Point p3, Graphics2D g2d){
        int[] arrayX = arrayMaker(p1.getX(), p2.getX(), p3.getX());
        int[] arrayY = arrayMaker(p1.getY(), p2.getY(), p3.getY());
        Polygon p = new Polygon(arrayX, arrayY, 3 /* 3 is because of triangle has 3 vertices */);
        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(p);
    }
    
    private int[] arrayMaker(int p1, int p2, int p3){
        return new int[]{p1,p2,p3};
    }

}
