import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.xml.bind.DatatypeConverter;

/**
 * Declares the main panel which consists of menubar, panels, etc for the purpose of designing
 * The actionListeners here includes some variables which is linked with
 * 
 * @author Ankit Rouniyar
 * @version 1.0
 */

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	
	private JPanel outputpanel, patternpanel;
	private JTextArea outputs, patlist;
	private JButton clear1, clear2;
    BytePattern forbytes = new BytePattern();
    
	ArrayList<byte[]> addBytes=new ArrayList<byte[]>();
	 private byte[] checkPos=new byte[100];
	 
	//byte[] pA=new byte[] {0x41,0x42,0x43};
	//byte[] pB = new byte[]{0x58,0x59,0x5A};
	//BytePattern forpatterns = new BytePattern(pB);
	//BytePattern forpatterns = new BytePattern();
	
	int bytecount;
	
	JMenuItem loadpatern,about,files,dir; // setting up j menu item
   
	/**
	 * To set up the overall GUI design
	 * @return menuBar which is to be called in Driver
	 */
	JMenuBar setupMenu() {

	    
	    JMenuBar menuBar = new JMenuBar();
	    JMenu fileMenu = new JMenu("Load |");
	    fileMenu.setMnemonic(KeyEvent.VK_O);//adding mnemonics/shortcut keys
	    fileMenu.setToolTipText("Menu option");
		menuBar.add(fileMenu);
		 
		
		JMenu helpMenu = new JMenu("Help |");
		helpMenu.setMnemonic(KeyEvent.VK_H);//adding mnemonics/shortcut keys
		helpMenu.setToolTipText("Click on help for more details");
		menuBar.add(helpMenu);	
		
		//adding icon the menu items
		ImageIcon imgpat = new ImageIcon(new ImageIcon("images/loadpat.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
		
		loadpatern = new JMenuItem("Load Patterns",imgpat);
		loadpatern.setMnemonic(KeyEvent.VK_L);//adding mnemonics/shortcut keys
		loadpatern.setToolTipText("For loading patterns");
		loadpatern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		
		fileMenu.add(loadpatern);
		ActionListener acpattern = new PatternListener();
		loadpatern.addActionListener(acpattern);
				
		ImageIcon imgf =  new ImageIcon(new ImageIcon("images/fileimg.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        
		files = new JMenuItem("Select Files",imgf);
		files.setMnemonic(KeyEvent.VK_F);//adding mnemonics/shortcut keys
		files.setToolTipText("For loading Files");
		files.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		
		fileMenu.add(files);
		
		ActionListener acfiles = new FileListener();
		files.addActionListener(acfiles);
		
		ImageIcon imgdir = new ImageIcon(new ImageIcon("images/dirimg.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
		
		dir = new JMenuItem("Select Directory",imgdir);
		dir.setMnemonic(KeyEvent.VK_D);
		dir.setToolTipText("For loading Directoires");
		dir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		
		fileMenu.add(dir);
		
		ActionListener acdir = new DirListener();
		dir.addActionListener(acdir);
		
		ImageIcon imgabout = new ImageIcon(new ImageIcon("images/about.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
		about= new JMenuItem("About",imgabout);
		helpMenu.add(about);
		about.setMnemonic(KeyEvent.VK_I);
		about.setToolTipText("For details of the program");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
		
		//ImageIcon img =  new ImageIcon(new ImageIcon("images/exit.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        JMenu exitMenu = new JMenu("Exit");
		menuBar.add(exitMenu);
		exitMenu.setMnemonic(KeyEvent.VK_E);//adding mnemonics / shortcut keys
		exitMenu.setToolTipText("For exiting the program");
		
		//listener added so when the user clicks exit option the program gets terminated
		exitMenu.addActionListener(e -> System.exit(0));
		
		//-----------------------------------------------------------
		//for dailog box of about menu showing details
		//-----------------------------------------------------------
		about.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(null, "This applicaiton is designed to identify known byte-pattern(signatures)\n within file contents."
		        		                      +"\nThe agenda of this program is to read a file/directory and also pattern (signature) file \n and compare"
		        		                      + "both of them and display if the files contains similiar pattern of pattern file"
		        		                      +"\nAuthor : ANKIT ROUNIYAR"
		        		                      +"\n\u00a9 all rights reserved"); 
		    }
		});
		
		
		return menuBar;
}
	
MainPanel(){

	outputpanel = new JPanel();
	patternpanel = new JPanel();

	outputpanel.setPreferredSize(new Dimension(480,540));
	patternpanel.setPreferredSize(new Dimension(480,540));
	
	
	
	outputpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
	outputs = new JTextArea("");
	outputs.setBackground(Color.lightGray);
	//outputs.setBackground(new Color(1,1,1, (float) 0.01));
    outputs.append("The Result is Displayed Here.");
	outputs.setPreferredSize(new Dimension(460,540));
	outputs.setBorder ( new TitledBorder ("OUTPUTS") );
    
	JScrollPane scroll = new JScrollPane (outputs);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	
	Font boldFont=new Font(outputs.getFont().getName(), Font.BOLD, outputs.getFont().getSize());
    outputs.setFont(boldFont); // bold text 
	
	//outputpanel.setLayout(new BoxLayout(outputpanel, BoxLayout.Y_AXIS));
	outputpanel.add(scroll);
	
	Icon icon = new ImageIcon(new ImageIcon("images/clear.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
	
     	
	clear1 = new JButton("Clear",icon);
	clear1.setFont(boldFont);
	outputpanel.add(clear1);
	
	clear1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			outputs.setText(" The Result is Displayed here.\n");
		}
	});
	
	//JScrollPane scroll2 = new JScrollPane (patlist);
    //scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	
	patternpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
	patlist = new MyTextArea(30,30);
	patlist.setBackground(new Color(1,1,1, (float) 0.01));
    //patlist.setBackground(Color.green);
	patlist.append(" Patterns Displayed Here.\n Pre-Defined Pattern \n");
	patlist.setPreferredSize(new Dimension(480,540));
	patlist.setBorder ( new TitledBorder ("Patterns" ) );
	
	Font f=new Font(patlist.getFont().getName(),Font.ITALIC+Font.BOLD,patlist.getFont().getSize());

	patlist.setFont(f);
	//patlist.setFont(patlist.getFont().deriveFont(14f)); 
	patternpanel.add(patlist);
	
	
	clear2 = new JButton("Clear",icon);
	clear2.setFont(f);
	//outputpanel.setLayout(null);
	patternpanel.add(clear2);
	
	clear2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			//forbytes.clearAll();
			patlist.setText(" Patterns Displayed Here.\n");
		}
	});
	
	this.setLayout(new GridLayout(1,2));
		
    add(outputpanel);
	add(patternpanel);
	
	//clear2 = new JButton("Clear");
	//add(clear2, BorderLayout.EAST);
	  File fi=new File("patterns.txt");
		
		patlist.append("Filename : "+fi.getName()+"\n");
		
		try {
			 @SuppressWarnings("resource")
			BufferedReader br=new BufferedReader(new FileReader(fi));
			 String text;
			 //boolean error;
			 while((text=br.readLine())!=null) {
					
						addBytes.add(BytePattern.hexToByte(forbytes.parseLine(text)));
					    patlist.append(text+"\n");
				}
		}
		catch(FileNotFoundException c) {
			c.getMessage();
			patlist.append("File Not Found!!!\n");
		}
		catch(IOException c) {
			c.getMessage();
		}
   } 

private class FileListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		bytecount=0;
		JFileChooser filechooser=new JFileChooser(".//");
		//filechooser.setFileSelectionMode(filechooser.DIRECTORIES_ONLY);
		int status=filechooser.showOpenDialog(null);
		if(status==JFileChooser.APPROVE_OPTION) {
			File f=filechooser.getSelectedFile();
			@SuppressWarnings("unused")
			boolean patternfound;
			outputs.append("\nFilename : "+f.getName()+"\n");
			InputStream in=null;
			
			try {
				in=new BufferedInputStream(new FileInputStream(f));
				int next;
				String hexValue = "";
				//int counter1=0;
				//int counter2=0;
				
				
				while((next=in.read())!=-1) {
					byte nextByte=(byte) next;
					 bytecount++;
				 @SuppressWarnings("unused")
				boolean error;
                  	for(int i=0;i<addBytes.size();i++) {
							if(nextByte==addBytes.get(i)[checkPos[i]]) {
								checkPos[i]++;
								if(checkPos[i]==addBytes.get(i).length) {
									hexValue = String.format("0x%x",bytecount-(addBytes.get(i)).length);
									outputs.append("Pattern found : ");
									outputs.append(DatatypeConverter.printHexBinary(addBytes.get(i)));
									outputs.append(" at offset : "+ (bytecount-addBytes.get(i).length)+ " ("+ hexValue+")" +" within the file\n");
									checkPos[i]=0;
								}
									
								else {
									error=false;
									
								}
							}
							else {
								checkPos[i]=0;
							}
							
						}
				
				
				}
			}
			catch(FileNotFoundException e1) {
				e1.printStackTrace();
				outputs.append("File Not Found");
			}
			catch(IOException e1) {
				e1.printStackTrace();
			}
		
			}
		        
	              
	             
	}
	
	}
private class PatternListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		// ... Point the current directory.
		            addBytes.clear();
		           
		            //forbytes.clearPattern();
		            //forbytes.reset();
					JFileChooser fileChooser = new JFileChooser(".//");
					int status=fileChooser.showOpenDialog(null);
					if(status==JFileChooser.APPROVE_OPTION) {
						File f=fileChooser.getSelectedFile();
						
						patlist.append("Filename : "+f.getName()+"\n");
						
						try {
							 @SuppressWarnings("resource")
							BufferedReader br=new BufferedReader(new FileReader(f));
							 String text;
							 //boolean error;
							
							 while((text=br.readLine())!=null) {
								 try{  
										addBytes.add(BytePattern.hexToByte(forbytes.parseLine(text)));
									    patlist.append(text+"\n");
									        
							 }
								 catch(NullPointerException e){
										e.getMessage();
										patlist.append("->Pattern is not appropriate at this point\n so this line is ignored\n");
									} 
							
						}
							 }
						catch(FileNotFoundException c) {
							c.getMessage();
						}
						catch(IOException c) {
							c.getMessage();
						}
						
		
	                 }
					
                     
					
	}
}
private class DirListener implements ActionListener {

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent event) {
		bytecount = 0;
		JFileChooser filechooser=new JFileChooser(".//");
		filechooser.setFileSelectionMode(filechooser.DIRECTORIES_ONLY);
		int status=filechooser.showOpenDialog(null);
		if(status==JFileChooser.APPROVE_OPTION) {
			
			File f=filechooser.getSelectedFile();
			File[] files=f.listFiles();
			int fileCount=f.list().length;
			outputs.append("\nDirectory : "+ f.getName()+" (" +fileCount +") "+ "\n");
			for(File file:files){
				bytecount = 0;	
			@SuppressWarnings("unused")
			boolean patternfound;
			outputs.append("\nFilename : "+file.getName()+"\n");
			InputStream in=null;
			try {
				in=new BufferedInputStream(new FileInputStream(file));
				int next;
				String hexValue = "";
				
				while((next=in.read())!=-1) {
					byte nextByte=(byte) next;
					 bytecount++;
                     @SuppressWarnings("unused")
					boolean error = false;
                     
						for(int i=0;i<addBytes.size();i++) {
							if(nextByte==addBytes.get(i)[checkPos[i]]) {
								checkPos[i]++;
								if(checkPos[i]==addBytes.get(i).length) {
									hexValue = String.format("0x%x",bytecount-(addBytes.get(i)).length);
									outputs.append("Pattern found : ");
									outputs.append(DatatypeConverter.printHexBinary(addBytes.get(i)));
								    outputs.append(" at offset : "+(bytecount-(addBytes.get(i)).length)+ " ("+ hexValue+")" +" within the file\n");
								    checkPos[i]=0;
								}
								
								else {
									error=true;
									
								}
							}
							else {
								checkPos[i]=0;
							}
						}
						
				}
				
                    
                 
				
			}
			catch(FileNotFoundException e1) {
				e1.printStackTrace();
				outputs.append("Something Error");
				
			}
			catch(IOException e1) {
				e1.printStackTrace();
				
			}
		}
			}
			
		}
	    }
	}


