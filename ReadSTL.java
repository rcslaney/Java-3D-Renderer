import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadSTL {

	public static void main(String[] args) {
		String stlfile = "./src/SimpleCube.STL";
		
		int lines = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(stlfile))) {
            while (br.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        int numberOfTriangles = lines/7;
        Triangle[] triangles = new Triangle[numberOfTriangles];
        int line = 0;
        String contents;
        String[] x = new String[2];
        String[] y = new String[2];
        String[] z = new String[2];
        int triangleNo = 0;
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(stlfile))) {
            while ((contents = br.readLine()) != null) {
            	if((line - 3) % 7 < 3 && line > 2) {
            		triangleNo = (line - 3) / 7;
            		if(triangles[triangleNo] == null) {
            			triangles[triangleNo] = new Triangle();
            		}
        			x = contents.trim().split(" ")[1].split("e");
        			y = contents.trim().split(" ")[2].split("e");
        			z = contents.trim().split(" ")[3].split("e");
        			triangles[triangleNo].vectors[(line - 3) % 7] = new Vector(Double.parseDouble(x[0]) * Math.pow(10, Double.parseDouble(x[1])), Double.parseDouble(y[0]) * Math.pow(10, Double.parseDouble(y[1])), Double.parseDouble(z[0]) * Math.pow(10, Double.parseDouble(z[1])));
            	}
                line++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        for(int i = 0; i < triangles.length; i++) {
        	System.out.println(triangles[i]);
        }
	}

}
