
public class TestingClass {

	public static void main(String[] args) {
		Triangle test = new Triangle(new Vector(0, 0, 0), new Vector(1000, 0, 0), new Vector(1000, 1000, 0));
		Matrix ninety = new Matrix();
		ninety.xRotationMatrix(2.5 * Math.PI);
		System.out.println(test);
		test.transform(ninety);
		System.out.println(test);
	}

}
