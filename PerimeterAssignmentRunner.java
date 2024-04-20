import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int pointnum = 0;
        for (Point currPt : s.getPoints()) {
            pointnum = pointnum + 1;
        }
        return pointnum;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter = getPerimeter(s);
        int sidenumber = getNumPoints (s);
        double averagelength = perimeter/sidenumber;
        return averagelength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestside = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestside) {
                largestside = currDist;
            }
            prevPt = currPt;
        }
        return largestside;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        for (Point currPt : s.getPoints()) {
            double x_coordinate = currPt.getX();
            if (x_coordinate > largestX) {
                largestX = x_coordinate;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestperimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            System.out.println(length);
            if (length > largestperimeter) {
                largestperimeter = length;
            }
        }
        return largestperimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestperimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            System.out.println(length);
            if (length > largestperimeter) {
                temp = f;
                largestperimeter = length;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int pointsnumber = getNumPoints(s);
        double averagelength = getAverageLength(s);
        double largestside = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + pointsnumber);
        System.out.println("the average length of a side in your shape is " 
        + averagelength);
        System.out.println("largest side is " + largestside + " long");
        System.out.println("largest x is " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestperimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter is " + largestperimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String filename = getFileWithLargestPerimeter();
        System.out.println("file name with largest perimeter is " 
        + filename);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
