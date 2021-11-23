import java.awt.*;
import java.awt.geom.*; //For Path2D class.
import static java.lang.Math.*;

public class WhiteTriangle extends Path2D.Double{

    private static final long serialVersionUID = 1L;
    SGArrayList<Point> array = new SGArrayList<Point>();

    public WhiteTriangle(Point p1, Point p2, Point p3, Graphics2D g2d, int numberOfTimes, int length) {
        //Creating temporary points to paint it black.
        Point p1Temp = new Point(p1.getX(), p1.getY());
        Point p2Temp = new Point(p2.getX(), p2.getY());
        Point p3Temp = new Point(p3.getX(), p3.getY());

        for(int i=0; i<numberOfTimes; i++){
            //Adding points to data structure recursively
            recursivePointAdder(p1Temp, p2Temp, p3Temp, g2d, i+1);
            //Setting new big triangle points
            p1Temp.setX( p1Temp.getX() + length+10 );
            p2Temp.setX( p2Temp.getX() + length+10 );
            p3Temp.setX( p3Temp.getX() + length+10 );
        }

        //All points added to data sturcture so Painting to white every triangle point that is get recursively by recursivePointAdder
        for(int i=0; i<numberOfTimes; i++)
            whiteFiller(array, g2d, numberOfTimes);
        
    }

    public void recursivePointAdder(Point p1, Point p2, Point p3, Graphics2D g2d, int recursiveCount) {
        if(recursiveCount == 0 )
            return; //Stop adding points
        
        //Creating temporary points to paint it black.
        Point p1Temp = new Point();
        Point p2Temp = new Point();
        Point p3Temp = new Point();

        p1Temp.setX( (int)ceil((p1.getX() + p2.getX())/2) );
        p1Temp.setY( (int)ceil((p1.getY() + p2.getY())/2) );
        
        p2Temp.setX( (int)floor((p2.getX() + p3.getX())/2) );
        p2Temp.setY( (int)floor((p2.getY() + p3.getY())/2) );
        
        p3Temp.setX( (int)floor((p1.getX() + p3.getX())/2) );
        p3Temp.setY( (int)floor((p1.getY() + p3.getY())/2) );
        
        array.add(p1Temp, p2Temp, p3Temp);
        //Painting inside of triangles recursively
        recursivePointAdder(p3Temp, p2Temp, p3, g2d, recursiveCount-1);
        array.add(p1Temp, p2Temp, p3Temp);
        recursivePointAdder(p1Temp, p2, p2Temp, g2d, recursiveCount-1);
        array.add(p1Temp, p2Temp, p3Temp);
        recursivePointAdder(p1, p1Temp, p3Temp, g2d, recursiveCount-1);
        array.add(p1Temp, p2Temp, p3Temp);
        
        // Switching to next big triangle recursively
        recursivePointAdder(p1, p2, p3, g2d, recursiveCount-1);
    }

    public void whiteFiller(SGArrayList<Point> array, Graphics2D g2d, int numberOfTimes){
        for(int i=0; i<array.size(); i++){
            Point p1 = array.get(i)[0];
            Point p2 = array.get(i)[1];
            Point p3 = array.get(i)[2];

            //ÇÇ Try to find painting color with double points..
            int[] arrayX = { p1.getX(), p2.getX(), p3.getX() };
            int[] arrayY = { p1.getY(), p2.getY(), p3.getY() };
            Polygon p = new Polygon(arrayX, arrayY, 3 /* 3 is because of triangle has 3 vertices */);
            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(p);
        }
    }

}