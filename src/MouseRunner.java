import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseRunner extends JFrame{
	private JLabel lblHexOut, lblDecOut,  lblColor;
	private JPanel mainPanel;
	public MouseRunner(){
		lblHexOut = new JLabel();
		lblDecOut = new JLabel();
		lblColor = new JLabel();
		mainPanel = new JPanel();
		
		lblHexOut.setBackground(Color.WHITE);
		lblHexOut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblHexOut.setOpaque(true);
		
		lblDecOut.setBackground(Color.WHITE);
		lblDecOut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblDecOut.setOpaque(true);
		
		
		mainPanel.add(lblHexOut);
		mainPanel.add(lblDecOut);
		
		this.setSize(new Dimension(1000, 1000));
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void setText(String decString, String hexString, Color c){
		this.lblDecOut.setText(decString);
		this.lblHexOut.setText(hexString);
		this.mainPanel.setBackground(c);
		this.repaint();
	}
	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		//DEBUG
		System.out.println(Arrays.toString(LinearColorVectorCalc.calcVectorPoints(0, 20, 10)));
		//GUI object
		MouseRunner gui = new MouseRunner();
		gui.setVisible(true);
		//Pixel Color variables
		Robot robot;
		Point m;
		Color c;
		int refresh = 10;
		String hexstring, colorstring = "";
		//loop, runs every n milliseconds, where n=refresh
		while (true){
			robot = new Robot();
			m = MouseInfo.getPointerInfo().getLocation();
			c = robot.getPixelColor(m.x, m.y);
			hexstring = "#"+dec2m(c.getRed(),16)+dec2m(c.getGreen(),16)+dec2m(c.getBlue(),16);
			colorstring = "" + String.format("%3s", c.getRed()) + ", " 
							 + String.format("%3s", c.getGreen()) + ", " 
							 + String.format("%3s", c.getBlue());
			gui.setText(colorstring, hexstring, c);
			Thread.sleep(refresh);
		}
	}
	
	//converts integer N to a base m number.
	static String dec2m(int N, int m) {
        String s = "";
        if(N==0)
        	return "00";
        
        for (int n = N; n > 0; n /= m) {
            int r = n % m;
            s = r < 10 ? r + s : (char) ('A' - 10 + r) + s;
        }
        return s.length()==1 ? "0"+s : s;
    }
	

}
