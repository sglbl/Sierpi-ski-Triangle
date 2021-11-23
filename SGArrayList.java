import java.util.*;
/**
 * @author Suleyman Golbol
 */

//ArrayList Data Structure with extra Point[] adding method.
public class SGArrayList<T> extends ArrayList<Point[]>{
    private static final long serialVersionUID = 1L;

    public void add(Point point1, Point point2, Point point3) {
        Point[] array = {point1, point2, point3};
        this.add (array);
    }
    

    
}
