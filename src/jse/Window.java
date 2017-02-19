package jse;

import java.awt.*;
import javax.swing.JFrame;

public class Window extends JFrame {

	// Window Configuration Variables
	int windowWidth = 650, windowHeight = 450;
	final boolean RESIZABLE = false;
	Insets insets;
	
	// User-Entered Variables
	String query;
	
	// Enumerators
	enum SearchTypes {
		AND,
		OR,
		PHRASE
	}
	
	/**
	 * Constructor
	 */
	public Window() {
		initialize();
	}
	
	/**
	 * Initializes all of the components for the window
	 */
	final void initialize() {
		// TODO Determine the size of all components below
		// Forms the window for the GUI
		setTitle("JSE - Javanonymous");
		setSize(windowWidth, windowHeight);
		setResizable(RESIZABLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setFocusable(true);
		
		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right, insets.top + windowHeight + insets.bottom);

		// Search box
		

		// Radio buttons for search type
		

		// Submit button
		

		// Text box for results
		
	}
}
