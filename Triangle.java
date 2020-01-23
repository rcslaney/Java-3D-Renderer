import java.awt.Polygon;

public class Triangle {
	public Vector[] vectors;
	
	public Triangle(Vector vectorA, Vector vectorB, Vector vectorC) {
		this.vectors = new Vector[3];
		this.vectors[0] = vectorA;
		this.vectors[1] = vectorB;
		this.vectors[2] = vectorC;
	}
	
	public Triangle() {
		this.vectors = new Vector[3];
	}
	
	public Triangle perspectiveTransform(double screenDistance) {
		return new Triangle(this.vectors[0].perspectiveTransform(screenDistance), this.vectors[1].perspectiveTransform(screenDistance), this.vectors[2].perspectiveTransform(screenDistance));
	}
	
	public Triangle translate(double x, double y, double z) {
		return new Triangle(this.vectors[0].add(x, y, z), this.vectors[1].add(x, y, z), this.vectors[2].add(x, y, z));
	}
	
	public Triangle transform(Matrix transformation) {
		return new Triangle(this.vectors[0].transform(transformation), this.vectors[1].transform(transformation), this.vectors[2].transform(transformation));
	}
	
	public Triangle multiply(double scalar) {
		return new Triangle(vectors[0].multiply(scalar), vectors[1].multiply(scalar), vectors[2].multiply(scalar));
	}
	
	public Polygon poly() {
		int xPoly[] = {(int)vectors[0].x, (int)vectors[1].x, (int)vectors[2].x};
		int yPoly[] = {(int)vectors[0].y, (int)vectors[1].y, (int)vectors[2].y};
		return new Polygon(xPoly, yPoly, 3);
	}
	
	@Override
    public String toString() {
        return "(" + this.vectors[0].toString() + ", " + this.vectors[1] + ", " + this.vectors[2] + ")";
    }
}
