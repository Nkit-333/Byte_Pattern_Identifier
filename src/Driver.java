import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 * Driver class that holds JFrame 
 * 
 * @author Ankit Rouniyar
 * @version 1.0
 */


public class Driver {
	public static void main(String[] args)
	  
	   {
	   
	   JFrame frame = new JFrame("Byte Scanner");
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close
	   MainPanel panel = new MainPanel();    // this would normally be a class that extends JPanel
	   panel.setPreferredSize(new Dimension(1000, 600));
	   
	   //setting up border
		panel.setBorder(BorderFactory.createLineBorder(Color.black,2));
		frame.setLayout(new FlowLayout ());
		// adding frame 
	  frame.setJMenuBar(panel.setupMenu());
	   frame.getContentPane().add(panel);
	   frame.pack();
	 //setting up in the center of the screen
	   frame.setLocationRelativeTo(null);
	   frame.setVisible(true);

	}
}
