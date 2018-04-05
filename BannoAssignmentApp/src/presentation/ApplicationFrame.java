package presentation;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import components.*;

/** 
 * The WebScraperFrame class contains a Frame provides a UI
 * 
 * @author Micah Reuscher
 */

public class ApplicationFrame extends JFrame {
	
	//--------------------------------------------------------------------------
    // Instance Variables
    //--------------------------------------------------------------------------
	
	public String urlSource;
	private JPanel urlPanel;
	private JPanel infoPanel;
	private JPanel urlPanel1;
	private JPanel infoPanel1;
	private JPanel infoField;
	private JPanel infoField1;
	
	/**
	 * Auto-generated serialVersionUID
	 */
	private static final long serialVersionUID = -5429031120769334611L;
	
    //--------------------------------------------------------------------------
    // Default Constructor
    //--------------------------------------------------------------------------  
	/**
	 * Build WebScraperFrame
	 * @throws Exception
	 */
	public ApplicationFrame() throws Exception {		
     		
		// Initialize frame properties
		this.setLayout(new GridLayout(0,1));
		//this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(Color.GRAY);
		this.setSize(350,300);
		this.setTitle( "HTML Data " );
		this.setResizable(false);
		
		// Create information field panels
		this.createInfoField();
		this.createInfoField1();
		
		// Build information field panels
		this.add(infoField);
		this.add(infoField1);
		}
		
		//---------------------------------------------
		// Class Methods
		//---------------------------------------------
		/**
		 * Build Build information panel
		 * @throws Exception 
		 */
	
		private void createInfoField() throws Exception {
			
			// Instantiate webpage
			Webpage webpage = new Webpage("https://banno.com");
			
			// Set up information field
			infoField = new JPanel();
			infoField.setLayout(new BorderLayout());
			infoField.setOpaque(false);
			
			// Set up urlPanel
			urlPanel = new JPanel();
			urlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			urlPanel.setOpaque(false);
			JLabel url = new JLabel("Source: "+webpage.getUrl());
			
			// Add UrlPanel to information field
			urlPanel.add(url);
			
			// Setup infoPanel
			infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(0,2,5,5));
			infoPanel.setOpaque(false);
			
			//Output: twitter icon/twitter  handle
			ImageIcon icon = new ImageIcon("src/components/twitter1.png");
			Image img = icon.getImage() ;  
			Image newImage = img.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH) ;
			ImageIcon newIcon = new ImageIcon(newImage);
			JLabel twitterHandle = new JLabel(" Twitter Handle: ");
	        twitterHandle.setIcon(newIcon);
	        infoPanel.add(twitterHandle);
	        JLabel twitterInfo = new JLabel(webpage.getTwitterHandle());
	        infoPanel.add(twitterInfo);
	        
	        //Output: 'Financial Institution' occurrences
	        JLabel stringCount = new JLabel("  Financial Institution: ");
	        infoPanel.add(stringCount);
	        
	        JLabel stringInfo = new JLabel("appeared "+webpage.getStringCount()+" times");
	        infoPanel.add(stringInfo);
	        
	        //Output: .png image count
	        JLabel pngCount = new JLabel("  '.png' Images: ");
	        infoPanel.add(pngCount);
	        
	        JLabel pngInfo = new JLabel("Total: "+webpage.getPngCount());
	        infoPanel.add(pngInfo);
	        
	        //Output: Most frequent alphanumerical character
	        JLabel firstCharacter = new JLabel("  Top Character: '");
	        infoPanel.add(firstCharacter);
	        
	        JLabel firstChInfo = new JLabel("'"+webpage.getChar1()+"' appeared "+webpage.getChar1Count()+" times");
	        infoPanel.add(firstChInfo);
	        
	        //Output: Second most frequent alphanumerical character
	        JLabel secondCharacter = new JLabel("  Second Character: '");
	        infoPanel.add(secondCharacter);
	        
	        JLabel secondChInfo = new JLabel("'"+webpage.getChar2()+"' appeared "+webpage.getChar2Count()+" times");
	        infoPanel.add(secondChInfo);
	        
	        //Output: Third most frequent alphanumerical character
	        JLabel thirdCharacter = new JLabel("  Third Character: '");
	        infoPanel.add(thirdCharacter);
	        
	        JLabel thirdChInfo = new JLabel("'"+webpage.getChar3()+"' appeared "+webpage.getChar3Count()+" times");
	        infoPanel.add(thirdChInfo);
			
	        // Adding panels to information field
			infoField.add(urlPanel, BorderLayout.NORTH);
			infoField.add(infoPanel, BorderLayout.CENTER);
			}
		
		/**
		 * Build information panel
		 * @throws Exception
		 */
		private void createInfoField1 () throws Exception {
			
			// Initialize Features page
			Webpage webpage1 = new Webpage("https://banno.com/features/");
			
			// Initialize field to contain Feature page information
			infoField1 = new JPanel();
			infoField1.setLayout(new FlowLayout());
			infoField1.setOpaque(false);
			
			// Panel to show source
			urlPanel1 = new JPanel();
			urlPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
			urlPanel1.setOpaque(false);
			JLabel url1 = new JLabel("Source: "+webpage1.getUrl());
			urlPanel1.add(url1);
			
			// Panel to show information
			infoPanel1 = new JPanel();
			infoPanel1.setLayout(new FlowLayout());
			infoPanel1.setOpaque(false);
			JLabel products = new JLabel(" Products/Features: '");
	        infoPanel1.add(products);
	        JLabel productCount = new JLabel("Total: "+webpage1.getProductCount());
			infoPanel1.add(productCount);
			
			// Add both Panels to Field
			infoField1.add(urlPanel1);
			infoField1.add(infoPanel1);
			}
	}	

