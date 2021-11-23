import java.awt.*;
import java.awt.geom.*; //For Path2D class.
import static java.lang.Math.*;

public class WhiteTriangle extends Path2D.Double{

    private static final long serialVersionUID = 1L;

    public WhiteTriangle(Point p1, Point p2, Point p3, Graphics2D g2d, int numberOfTimes) {
        //Creating temporary points to paint it black.
        Point p1Temp = new Point(p1.getX(), p1.getY());
        Point p2Temp = new Point(p2.getX(), p2.getY());
        Point p3Temp = new Point(p3.getX(), p3.getY());

        for(int i=0; i<numberOfTimes; i++){
            //Recursively painting to white every triangle
            recursiveWhitePainter(p1Temp, p2Temp, p3Temp, g2d, i+1);
            p1Temp.setX( p1Temp.getX() + 130 );
            p2Temp.setX( p2Temp.getX() + 130 );
            p3Temp.setX( p3Temp.getX() + 130 );
        }
        
    }

    public void recursiveWhitePainter(Point p1, Point p2, Point p3, Graphics2D g2d, int recursiveCount) {
        if(recursiveCount == 0 )
            return; //Stop painting to white.

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

        // g2d.setColor(Color.BLACK);
        // moveTo( p1.getX(), p1.getY() );
        // lineTo( p2.getX(), p2.getY() );
        // lineTo( p3.getX(), p3.getY() );
        // closePath();

        // p1 = p1Temp;    p2 = p2Temp;    p3 = p3Temp;
        whiteFiller(p1Temp, p2Temp, p3Temp, g2d);
        
        //Painting inside of triangles recursively
        recursiveWhitePainter(p3Temp, p2Temp, p3, g2d, recursiveCount-1);
        recursiveWhitePainter(p1Temp, p2, p2Temp, g2d, recursiveCount-1);
        recursiveWhitePainter(p1, p1Temp, p3Temp, g2d, recursiveCount-1);

        // Switching to next big triangle recursively
        recursiveWhitePainter(p1, p2, p3, g2d, recursiveCount-1);
    }

    public void whiteFiller(Point p1, Point p2, Point p3, Graphics2D g2d){
        int[] arrayX = arrayMaker(p1.getX(), p2.getX(), p3.getX());
        int[] arrayY = arrayMaker(p1.getY(), p2.getY(), p3.getY());
        Polygon p = new Polygon(arrayX, arrayY, 3 /* 3 is because of triangle has 3 vertices */);
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(p);
    }

    private int[] arrayMaker(int p1, int p2, int p3){
        return new int[]{p1,p2,p3};
    }

}