public class Matrix {
	double[][] matrix;
	
	public Matrix(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
		this.matrix = new double[3][3];
		this.matrix[0][0] = a;
		this.matrix[0][1] = b;
		this.matrix[0][2] = c;
		this.matrix[1][0] = d;
		this.matrix[1][1] = e;
		this.matrix[1][2] = f;
		this.matrix[2][0] = g;
		this.matrix[2][1] = h;
		this.matrix[2][2] = i;
	}
	
	public Matrix() {
		this.matrix = new double[3][3];
		this.matrix[0][0] = 0;
		this.matrix[0][1] = 0;
		this.matrix[0][2] = 0;
		this.matrix[1][0] = 0;
		this.matrix[1][1] = 0;
		this.matrix[1][2] = 0;
		this.matrix[2][0] = 0;
		this.matrix[2][1] = 0;
		this.matrix[2][2] = 0;
	}
	
	public void setValue(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
		this.matrix[0][0] = a;
		this.matrix[0][1] = b;
		this.matrix[0][2] = c;
		this.matrix[1][0] = d;
		this.matrix[1][1] = e;
		this.matrix[1][2] = f;
		this.matrix[2][0] = g;
		this.matrix[2][1] = h;
		this.matrix[2][2] = i;
	}
	
	public Matrix xRotationMatrix(double degrees) {
		return new Matrix(1, 0, 0,
					  0, Math.cos(degrees), -Math.sin(degrees),
					  0, Math.sin(degrees), Math.cos(degrees));
	}
	
	public Matrix yRotationMatrix(double degrees) {
		return new Matrix(Math.cos(degrees), 0, Math.sin(degrees),
					  0, 1, 0,
					  -Math.sin(degrees), 0, Math.cos(degrees));
	}
	
	public Matrix zRotationMatrix(double degrees) {
		return new Matrix(Math.cos(degrees), -Math.sin(degrees), 0,
				      Math.sin(degrees), Math.cos(degrees), 0,
					  0, 0, 1);
	}
}
