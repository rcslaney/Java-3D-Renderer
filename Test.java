import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

final public class Test
{

    JFrame frame;
    DrawPanel drawPanel;

    double x = 100;
    double y = 700;

    double xVel = 10;
    double yVel = -1;

    public static void main(String... args)
    {
        new Test().go();
    }

    private void go()
    {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(1000, 1035);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        moveIt();
    }

    class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
        	NumberFormat formatter = new DecimalFormat("#0.00");    
            g.setColor(Color.BLACK);
            g.fillRect((int)Math.round(x), (int)Math.round(y), 6, 6);
            g.drawString("X velocity: " + formatter.format(xVel), 10, 20);
            g.drawString("Y velocity: " + formatter.format(yVel), 10, 40);
            g.drawString("              X: " + formatter.format(x), 10, 60);
            g.drawString("              Y: " + formatter.format(y), 10, 80);
        }
    }

    private void moveIt()
    {
    	Triangle testTriangle = new Triangle();
        while (true)
        {
            if (x + xVel >= 990) {
                xVel = -1 * Math.abs(xVel);
            }
            
            if(x + xVel <= 0) {
            	xVel = 1 * Math.abs(xVel);
            }
            
            if (y + yVel >= 1000) {
            	if(yVel > 0.1) {
            		yVel = -0.8 * Math.abs(yVel);
            	} else {
            		yVel = 0;
            	}
            }
            
            if(y + yVel <= 0) {
            	yVel = 0.8 * Math.abs(yVel);
            }
            
            if(yVel != 0) {
            	yVel += 0.0981;
            } else {
            	xVel = xVel * 0.99;
            	if(xVel < 0.1) {
            		xVel = 0;
            	}
            }
            
            x += xVel;
            y += yVel;
            
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