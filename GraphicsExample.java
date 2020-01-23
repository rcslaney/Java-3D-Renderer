import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;  
  
public class GraphicsExample extends Canvas{  
      
	private static final long serialVersionUID = 1L;
	public void paint(Graphics g) {  
        g.drawString("Hello",40,40);  
        setBackground(Color.WHITE);  
        g.fillRect(130, 30,100, 80);  
        g.drawOval(30,130,50, 60);  
        setForeground(Color.RED);  
        g.fillOval(130,130,50, 60);  
        g.drawArc(30, 200, 40,50,90,60);  
        g.fillArc(30, 130, 40,50,180,40); 
        for(int i = 0; i < 10; i++) {
        	g.drawOval(30 + i * 15, 130, 10, 10);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
          
    }  
        public static void main(String[] args) {  
        GraphicsExample m=new GraphicsExample();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(400,400);  
        //f.setLayout(null);  
        f.setVisible(true);  
    }  
  
}  