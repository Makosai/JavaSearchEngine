package jse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {	
	// Window Components
	JPanel searchPanel, resultsPanel, filePanel, managerPanel, centerPanel;
	
	JLabel searchBoxLabel;
	JTextField searchBox;
	
	Button submitButton;
	
	Button managerButton, addFileButton, removeFileButton, updateFileButton;
	
	ButtonGroup searchTypesGroup;
	JRadioButton searchTypesAND, searchTypesOR, searchTypesPHRASE;
	
	JTable searchResults, fileResults;
	
	// Window Configuration Variables
	int windowWidth = 650, windowHeight = 450;
	final boolean RESIZABLE = false;
	Insets insets;

	boolean managing = false;
	
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
		//setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right, insets.top + windowHeight + insets.bottom);
		
		// Search box
		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		searchBoxLabel = new JLabel("Search: ");
		searchPanel.add(searchBoxLabel);
		
		searchBox = new JTextField(25);
		searchBox.setSize(200, 25);
		searchBox.addActionListener(this);
		searchPanel.add(searchBox);

		// Radio buttons for search type
		searchTypesGroup = new ButtonGroup();
		
		searchTypesAND = new JRadioButton("All Terms");
		searchTypesGroup.add(searchTypesAND);
		searchPanel.add(searchTypesAND);
		
		searchTypesOR = new JRadioButton("Any Terms");
		searchTypesGroup.add(searchTypesOR);
		searchPanel.add(searchTypesOR);
		
		searchTypesPHRASE = new JRadioButton("Exact Phrase");
		searchTypesGroup.add(searchTypesPHRASE);
		searchPanel.add(searchTypesPHRASE);

		// Submit button
		submitButton = new Button("Submit");
		submitButton.addActionListener(this);
		searchPanel.add(submitButton);
		
		searchPanel.setVisible(true);

		// Table for results
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
		
		Object[][] searchData = new Object[][] {
				{"name.txt"},
				{"name2.txt"}
		};
		String[] searchColumns = new String[] { "Search Results" };
		
		
		searchResults = new JTable(searchData, searchColumns);
		resultsPanel.add(new JScrollPane(searchResults));
		
		// Table and buttons for file manager
		filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
		
		Object[][] fileData = new Object[][] {
				{"name.txt", "status"},
				{"name2.txt", "status"}
		};
		String[] fileColumns = new String[] { "Files", "Status" };
		
		
		fileResults = new JTable(fileData, fileColumns);
		filePanel.add(new JScrollPane(fileResults));
		
		// Manager & File Buttons
		managerPanel = new JPanel();
		managerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		addFileButton = new Button("Add File");
		managerPanel.add(addFileButton);
		
		removeFileButton = new Button("Remove Files");
		managerPanel.add(removeFileButton);
		
		updateFileButton = new Button("Update Files");
		managerPanel.add(updateFileButton);
		
		managerButton = new Button("File Manager");
		managerButton.addActionListener(this);
		managerPanel.add(managerButton);
		
		// Panels and Pack
		getContentPane().add(searchPanel, BorderLayout.NORTH);
		getContentPane().add(filePanel, BorderLayout.CENTER);
		getContentPane().add(resultsPanel, BorderLayout.CENTER);
		getContentPane().add(managerPanel, BorderLayout.SOUTH);

		ShowManager(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchBox || e.getSource() == submitButton) {
			query = searchBox.getText();
			
			Search(query);
			
			if(JavaSearchEngine.debugging)
				System.out.println("Query: " + query);
		}
		
		if(e.getSource() == managerButton) {
			ShowManager(!managing);
		}
	}
	
	public void ShowManager(boolean show) {
		managing = show;
		
		getContentPane().remove(show ? resultsPanel : filePanel);
		getContentPane().add(show ? filePanel : resultsPanel, BorderLayout.CENTER);
		
		managerButton.setLabel(show ? "Search Results" : "File Manager");
		addFileButton.setVisible(show);
		removeFileButton.setVisible(show);
		updateFileButton.setVisible(show);
		
		pack();
	}
	
	public void Search(String text) {
		if(managing) {
			ShowManager(false);
		}
	}
}
