package solving_problem_with_software.perimeter_quiz;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

import java.io.File;


public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
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

    public int getNumPoints(Shape s) {
        // count no. of points
        int countNumPoints = 0;
        for (Point currPoint : s.getPoints()) {
            countNumPoints = countNumPoints + 1;
        }
        return countNumPoints;
    }

    public double getAverageLength(Shape s) {

        double averageLength = 0.0;
        averageLength = getPerimeter(s) / getNumPoints(s);

        return averageLength;
    }

    public double getLargestSide(Shape s) {

        //start with currLargestSide=0.0
        double currLargestSide = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update currLargestSide
            if (currDist > currLargestSide) {
                currLargestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return currLargestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }

        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double currPerimeter = getPerimeter(shape);
            if (currPerimeter > largestPerimeter) {
                largestPerimeter = currPerimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {

        File temp = null;

        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double currPerimeter = getPerimeter(shape);
            if (currPerimeter > largestPerimeter) {
                largestPerimeter = currPerimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numOfPointsInFile = getNumPoints(s);
        System.out.println("no of points = " + numOfPointsInFile);
        double averageLength = getAverageLength(s);
        System.out.println(averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("largest side length: " + largestSide);
        double largestXValue = getLargestX(s);
        System.out.println("largest x value :" + largestXValue);
    }

    public void testPerimeterMultipleFiles() {
        double largestPerimeterMultipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter: " + largestPerimeterMultipleFiles);
    }

    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("file name with largest perimeter: " + fileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testFileWithLargestPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}
