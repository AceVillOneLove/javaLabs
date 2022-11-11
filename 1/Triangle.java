import java.text.DecimalFormat;
public class Triangle{
    private Point[] sides = new Point[3];
    private String triangleType;
    private double area;
    private double perimeter;
    private double edgeSizes[] = new double[3];
    public Triangle() {
        for(int i = 0; i < 3; i++) {
            sides[i] = new Point((Math.random() * 9 + 1),
                    (Math.random() * 9 + 1));
        }
        edgeSizes[0] = calcEdge(sides[0], sides[1]);
        edgeSizes[1] = calcEdge(sides[0], sides[2]);
        edgeSizes[2] = calcEdge(sides[1], sides[2]);
        area = calcArea();
        perimeter = calcPerimeter();
        triangleType = typeChecker();
    }
    public Triangle(Point dotOne, Point dotTwo, Point dotThree) {
        sides[0] = dotOne;
        sides[1] = dotTwo;
        sides[2] = dotThree;
        edgeSizes[0] = calcEdge(sides[0], sides[1]);
        edgeSizes[1] = calcEdge(sides[0], sides[2]);
        edgeSizes[2] = calcEdge(sides[1], sides[2]);
        area = calcArea();
        perimeter = calcPerimeter();
        triangleType = typeChecker();
    }
    public String getTriangleType() {
        return triangleType;
    }
    public double getArea() {
        return area;
    }
    public double getPerimeter(){
        return perimeter;
    }

    public double calcPerimeter() {
        double perimeter = 0;
        perimeter += edgeSizes[TriangleSides.AB.ordinal()] +
                edgeSizes[TriangleSides.AC.ordinal()] +
                edgeSizes[TriangleSides.BC.ordinal()];
        return perimeter;
    }
    public double calcEdge(Point dot1, Point dot2) {
        double edge = 0;
        edge += Math.sqrt(Math.pow(dot1.getX() - dot2.getX(), 2) +
                Math.pow(dot1.getY() - dot2.getY(), 2));
        return edge;
    }
    public double calcArea() {
        double area = 0;
        double semiPerim = calcPerimeter() / 2;
        area += Math.sqrt(semiPerim * (semiPerim - calcEdge(sides[0], sides[1])) *
                (semiPerim - calcEdge(sides[1], sides[2])) *
                (semiPerim - calcEdge(sides[0], sides[2]))
        );
        return area;
    }
    public String toString() {
        DecimalFormat dF = new DecimalFormat( "#.###" );
        return "Point 1: (" + dF.format(sides[0].getX()) + ", " + dF.format(sides[0].getY()) + ")\n" +
                "Point 2: (" + dF.format(sides[1].getX()) + ", " + dF.format(sides[1].getY()) + ")\n" +
                "Point 3: (" + dF.format(sides[2].getX()) + ", " + dF.format(sides[2].getY()) + ")\n" +
                "Perimeter: " + dF.format(perimeter) + "\n" +
                "Area: " + dF.format(area) + "\n" +
                "TriangleType: " + triangleType + ".\n";
    }
    public String typeChecker() {
        if(Main.isEqual(edgeSizes[TriangleSides.AB.ordinal()],
                edgeSizes[TriangleSides.BC.ordinal()]) &&
                Main.isEqual(edgeSizes[TriangleSides.AC.ordinal()],
                        edgeSizes[TriangleSides.AB.ordinal()])) {
            return "equilateral triangle";
        }
        else if(Main.isEqual(Math.pow(edgeSizes[TriangleSides.AB.ordinal()], 2) +
                        Math.pow(edgeSizes[TriangleSides.AC.ordinal()], 2),
                Math.pow(edgeSizes[TriangleSides.BC.ordinal()], 2)) ||
                Main.isEqual(Math.pow(edgeSizes[TriangleSides.AB.ordinal()], 2) +
                                Math.pow(edgeSizes[TriangleSides.BC.ordinal()], 2),
                        Math.pow(edgeSizes[TriangleSides.AC.ordinal()], 2)) ||
                Main.isEqual(Math.pow(edgeSizes[TriangleSides.AC.ordinal()], 2) +
                                Math.pow(edgeSizes[TriangleSides.BC.ordinal()], 2),
                        Math.pow(edgeSizes[TriangleSides.AB.ordinal()], 2)))  {
            return "square corner triangle";
        }
        else if(Main.isEqual(edgeSizes[TriangleSides.AB.ordinal()], edgeSizes[TriangleSides.AC.ordinal()]) ||
                Main.isEqual(edgeSizes[TriangleSides.AB.ordinal()], edgeSizes[TriangleSides.BC.ordinal()]) ||
                Main.isEqual(edgeSizes[TriangleSides.AC.ordinal()], edgeSizes[TriangleSides.BC.ordinal()])) {
            return "isosceles triangle";
        }
        else {
            return "arbitrary triangle";
        }
    }
}
