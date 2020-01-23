import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ThreeDimensional {
	static JFrame frame;
    static DrawPanel drawPanel;
    
    int width = 1280;
    int height = 1280;
    
    int screenDist = 80000;
    double scale = 1.2;
    
    long totalDrawTime = 0;
    long totalTransformTime = 0;
    
    int totalFrames = 0;
    
    Triangle[] triangles;
    
    double rotationX = 0;
    double rotationY = 0;
    
    double initialRotationY, initialRotationX;
    
    int initialMouseX, initialMouseY;
    
    public void drawTriangle(Triangle triangle, Graphics g) {
    	triangle = triangle.translate(0,  0,  4000).multiply(scale).translate(width*scale/2, height*scale/2, 0);
    	g.drawPolygon(triangle.poly());
    }
    
	class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        
        public DrawPanel() {
        	MouseHandler handler = new MouseHandler();
        	this.addMouseWheelListener(handler);
        	this.addMouseMotionListener(handler);
        	this.addMouseListener(handler);
        }

        public void paintComponent(Graphics g)
        {
        	//long startTime = System.currentTimeMillis();
        	totalFrames += 1;
        	Matrix rotationMatrixY = new Matrix().xRotationMatrix(rotationY * Math.PI);
        	Matrix rotationMatrixX = new Matrix().yRotationMatrix(rotationX * Math.PI);
        	
        	try {
	        	for(int i = 0; i < triangles.length; i++) {
	        		try {
	        			drawTriangle(triangles[i].transform(rotationMatrixX).transform(rotationMatrixY), g);
	        		} catch(Exception e) {
	        			
	        		}
	        	}
        	} catch(Exception e) {
	        	
	        }
        }
    }
	
	private class MouseHandler extends MouseAdapter
	{  
	    @Override
	    public void mouseWheelMoved(MouseWheelEvent e) {
	    	scale -= e.getPreciseWheelRotation() * e.getScrollAmount() / 100;
	    	System.out.println(String.valueOf(e.getPreciseWheelRotation()) + ":" + String.valueOf(e.getScrollAmount()));
	    }
	    
	    public void mouseDragged(MouseEvent e) {
	        rotationX = initialRotationX - (double)(e.getX() - initialMouseX) / 1000;
	        rotationY = initialRotationY + (double)(e.getY() - initialMouseY) / 1000;
	    }
	    
	    public void mousePressed(MouseEvent e) {
	        initialMouseX = e.getX();
	        initialMouseY = e.getY();
	        initialRotationX = rotationX;
	        initialRotationY = rotationY;
	     }
	}
	
    public static void main(String... args)
    {
        new ThreeDimensional().go();
    }

    private void go()
    {
        System.out.println("Begin parsing.");
        
        String stlfile = "./src/NotSoSimple.STL";
		
		int lines = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(stlfile))) {
            while (br.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        int numberOfTriangles = lines / 7;
        triangles = new Triangle[numberOfTriangles];
        int line = 0;
        String contents;
        double x, y, z;
        int triangleNo = 0;
        
        double maxX = 0;
        double maxY = 0;
        double maxZ = 0;
        double minX = 0;
        double minY = 0;
        double minZ = 0;
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(stlfile))) {
            while ((contents = br.readLine()) != null) {
            	if((line - 3) % 7 < 3 && line > 2) {
            		triangleNo = (line - 3) / 7;
            		if(triangles[triangleNo] == null) {
            			triangles[triangleNo] = new Triangle();
            		}
        			x = Double.parseDouble(contents.trim().split(" ")[1].split("e")[0]) * Math.pow(10, Double.parseDouble(contents.trim().split(" ")[1].split("e")[1]));
        			y = Double.parseDouble(contents.trim().split(" ")[2].split("e")[0]) * Math.pow(10, Double.parseDouble(contents.trim().split(" ")[2].split("e")[1]));
        			z = Double.parseDouble(contents.trim().split(" ")[3].split("e")[0]) * Math.pow(10, Double.parseDouble(contents.trim().split(" ")[3].split("e")[1]));
        			triangles[triangleNo].vectors[(line - 3) % 7] = new Vector(x, y, z);
        			maxX = Math.max(x, maxX);
        			maxY = Math.max(y, maxY);
        			maxZ = Math.max(z, maxZ);
        			minX = Math.min(x, minX);
        			minY = Math.min(y, minY);
        			minZ = Math.min(z, minZ);
            	}
            	
                line++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        System.out.println(Double.toString(maxX) + ", " + Double.toString(maxY) + ":" + Double.toString(minX) + ", " + Double.toString(minY));
        
        Matrix rotateZ = new Matrix().xRotationMatrix(0.5 * Math.PI);
        
        for(int i = 0; i < triangles.length; i++) {
        	triangles[i] = triangles[i].translate((minX - maxX) / 2, (minY - maxY) / 2, (minZ - maxZ) / 2).transform(rotateZ);
        }
        
        System.out.println("Parsing complete. Begin transform.");
        
        System.out.println("Transform complete. Begin rendering.");
        
        frame = new JFrame("3D Render");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(true);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        drawPanel.setDoubleBuffered(true);
        
        while (true) {
        	try
            {
                Thread.sleep(1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }

}
