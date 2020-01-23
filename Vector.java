public class Vector {
	public double x;
	public double y;
	public double z;
	
	public Vector(double xInit, double yInit, double zInit) {
		this.x = xInit;
		this.y = yInit;
		this.z = zInit;
	}
	
	public Vector() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public void setValue(double xVal, double yVal, double zVal) {
		x = xVal;
		y = yVal;
		z = zVal;
	}
	
	public double dot(Vector other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}
	
	public Vector cross(Vector other) {
		return new Vector(this.y * other.z - other.y * this.z, -(this.x * other.z - other.x * this.z), this.x * other.y - other.x * this.y);
	}
	
	public Vector add(Vector other) {
		return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
	}
	
	public Vector add(double x, double y, double z) {
		return new Vector(this.x + x, this.y + y, this.z + z);
	}
	
	public Vector subtract(Vector other) {
		return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
	}
	
	public Vector multiply(double scalar) {
		return new Vector(this.x * scalar, this.y * scalar, this.x * scalar);
	}
	
	public Vector perspectiveTransform(double screenDistance) {
		double scalar = screenDistance / this.z;
		return new Vector(this.x * scalar, this.y * scalar, 0);
	}
	
	public Vector transform(Matrix transformation) {
		return new Vector(transformation.matrix[0][0] * this.x + transformation.matrix[0][1] * this.y + transformation.matrix[0][2] * this.z,
				transformation.matrix[1][0] * this.x + transformation.matrix[1][1] * this.y + transformation.matrix[1][2] * this.z,
				transformation.matrix[2][0] * this.x + transformation.matrix[2][1] * this.y + transformation.matrix[2][2] * this.z
				);
	}
	
	@Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
