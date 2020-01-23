import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ThreeDimensional {
	static JFrame frame;
    static DrawPanel drawPanel;
    
    int width = 1000;
    int height = 1000;
    
    int x = -500;
    
    public Vector[] points = new Vector[8];
    public Vector[] pointsP = new Vector[8];
    
    public Vector a, b, c, d, e, f, g, h;
    
	class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics gr)
        {
        	int screenDist = 200;
        	for(int i = 0; i < points.length; i++) {
        		pointsP[i] = points[i].perspectiveTransform(screenDist);
        	}
        	
        	gr.drawLine((int)pointsP[0].x + width/2, (int)pointsP[0].y + height/2, (int)pointsP[1].x + width/2, (int)pointsP[1].y + height/2);
        	gr.drawLine((int)pointsP[1].x + width/2, (int)pointsP[1].y + height/2, (int)pointsP[2].x + width/2, (int)pointsP[2].y + height/2);
        	gr.drawLine((int)pointsP[2].x + width/2, (int)pointsP[2].y + height/2, (int)pointsP[3].x + width/2, (int)pointsP[3].y + height/2);
        	gr.drawLine((int)pointsP[3].x + width/2, (int)pointsP[3].y + height/2, (int)pointsP[0].x + width/2, (int)pointsP[0].y + height/2);
        	gr.drawLine((int)pointsP[0].x + width/2, (int)pointsP[0].y + height/2, (int)pointsP[4].x + width/2, (int)pointsP[4].y + height/2);
        	gr.drawLine((int)pointsP[1].x + width/2, (int)pointsP[1].y + height/2, (int)pointsP[5].x + width/2, (int)pointsP[5].y + height/2);
        	gr.drawLine((int)pointsP[2].x + width/2, (int)pointsP[2].y + height/2, (int)pointsP[6].x + width/2, (int)pointsP[6].y + height/2);
        	gr.drawLine((int)pointsP[3].x + width/2, (int)pointsP[3].y + height/2, (int)pointsP[7].x + width/2, (int)pointsP[7].y + height/2);
        	gr.drawLine((int)pointsP[4].x + width/2, (int)pointsP[4].y + height/2, (int)pointsP[5].x + width/2, (int)pointsP[5].y + height/2);
        	gr.drawLine((int)pointsP[5].x + width/2, (int)pointsP[5].y + height/2, (int)pointsP[6].x + width/2, (int)pointsP[6].y + height/2);
        	gr.drawLine((int)pointsP[6].x + width/2, (int)pointsP[6].y + height/2, (int)pointsP[7].x + width/2, (int)pointsP[7].y + height/2);
        	gr.drawLine((int)pointsP[7].x + width/2, (int)pointsP[7].y + height/2, (int)pointsP[4].x + width/2, (int)pointsP[4].y + height/2);
        }
    }
	
    public static void main(String... args)
    {
        new ThreeDimensional().go();
    }

    private void go()
    {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        while (true) {
        	points[0] = new Vector(-100 + x, 100, 200);
        	points[1] = new Vector(100 + x, 100, 200);
        	points[2] = new Vector(100 + x, -100, 200);
        	points[3] = new Vector(-100 + x, -100, 200);
        	points[4] = new Vector(-100 + x, 100, 400);
        	points[5] = new Vector(100 + x, 100, 400);
        	points[6] = new Vector(100 + x, -100, 400);
        	points[7] = new Vector(-100 + x, -100, 400);
        	
        	x++;
        	
        	try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }

}
