import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;
/**
 * For importing images to the JTextArea
 * 
 * @author Ankit Rouniyar
 * @version 1.0
 */


@SuppressWarnings("serial")
public class MyTextArea extends JTextArea {

	
    private Image img;

    
    /**
     * For inserting image for background
     * @param a for size
     * @param b for size
     */
    public MyTextArea(int a, int b) {
        super(a,b);
        try{
            img = ImageIO.read(new File("images/pic-ink.png"));
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Overrides the graphics 
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,05,05,this);
        super.paintComponent(g);
    }
}