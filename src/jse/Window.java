package jse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


/**
 * @author Team Javanonymous (Eric Pigott, Yosvany Reina, Kristopher Ali, Marcos Mendoza)
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {	
	// Window Components
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem aboutMenuItem;
	
	JPanel searchPanel, resultsPanel, filePanel, managerPanel, centerPanel;
	
	JLabel searchBoxLabel;
	JTextField searchBox;
	
	JButton submitButton;
	
	JButton managerButton, addFileButton, removeFileButton, updateFileButton;
	
	ButtonGroup searchTypesGroup;
	JRadioButton searchTypesAll, searchTypesAny, searchTypesPhrase;
	
	DefaultTableModel searchModel, fileModel;
	JTable searchResults, fileResults;
	
	// Management Components
	Data data = new Data();
	
	// Window Configuration Variables
	Color foregroundColor = new Color(255, 255, 255), backgroundColor = new Color(51, 51 ,51);
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
		// Forms the window for the GUI
		setTitle("JSE - Javanonymous");
		setSize(windowWidth, windowHeight);
		setResizable(RESIZABLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setFocusable(true);
		
		// Menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//// File Menu ////
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		aboutMenuItem = new JMenuItem("About");
		fileMenu.add(aboutMenuItem);
		
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
		
		searchTypesAll = new JRadioButton("All Terms");
		searchTypesGroup.add(searchTypesAll);
		searchPanel.add(searchTypesAll);
		
		searchTypesAny = new JRadioButton("Any Terms");
		searchTypesGroup.add(searchTypesAny);
		searchPanel.add(searchTypesAny);
		
		searchTypesPhrase = new JRadioButton("Exact Phrase");
		searchTypesGroup.add(searchTypesPhrase);
		searchPanel.add(searchTypesPhrase);

		// Submit button
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		searchPanel.add(submitButton);

		// Table for results
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
		
		Object[][] searchData = new Object[][] {
				{"name.txt"},
				{"name2.txt"}
		};
		String[] searchColumns = new String[] { "Search Results" };
		
		
		searchResults = new JTable(searchData, searchColumns);
		
		JScrollPane searchResultsScrollPane = new JScrollPane(searchResults);
		resultsPanel.add(searchResultsScrollPane);
		
		// Table and buttons for file manager
		filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
		
		fileModel = new DefaultTableModel();
		fileResults = new JTable(fileModel);
		for(String col : Constants.FILE_COLUMNS) {
			fileModel.addColumn(col);
		}
		fileModel.addRow(new Object[]{"name.txt", "status"});
		
		filePanel.add(new JScrollPane(fileResults));
		
		// Manager & File Buttons
		managerPanel = new JPanel();
		managerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		addFileButton = new JButton("Add File");
		managerPanel.add(addFileButton);
		addFileButton.addActionListener(this);
		
		removeFileButton = new JButton("Remove Files");
		removeFileButton.addActionListener(this);
		managerPanel.add(removeFileButton);
		
		updateFileButton = new JButton("Update Files");
		updateFileButton.addActionListener(this);
		managerPanel.add(updateFileButton);
		
		managerButton = new JButton("File Manager");
		managerButton.addActionListener(this);
		managerPanel.add(managerButton);
		
		// Panels and Pack
		getContentPane().add(searchPanel, BorderLayout.NORTH);
		getContentPane().add(filePanel, BorderLayout.CENTER);
		getContentPane().add(resultsPanel, BorderLayout.CENTER);
		getContentPane().add(managerPanel, BorderLayout.SOUTH);

		setGlobalColor(getContentPane(), getJMenuBar());
		
		ShowManager(false);
	}

	// Interacts and responds to user actions on the GUI interface.
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
		
		if(e.getSource() == updateFileButton) {
			//data.UpdateFiles();
		}

		if(e.getSource() == addFileButton) {
			FileManager.Add(data, fileModel);
		}
		
		if(e.getSource() == removeFileButton) {
			FileManager.Remove(data, fileResults.getSelectedRows(), fileModel);
		}

	}
	
	/**
	 * Sets the color for every possible component
	 * @param container The container for the JFrame
	 * @param menu The MenuBar for the JFrame
	 */
	public void setGlobalColor(Container container, JMenuBar menu)
	{
	    menu.setForeground(foregroundColor);
	    menu.setBackground(backgroundColor);
		menu.setBorderPainted(false);
	    
	    for(Component c : menu.getComponents()) {
	    	c.setForeground(foregroundColor);
            c.setBackground(backgroundColor);
	    }
	    
	    setGlobalColor(container);
	}
	
	/**
	 * Sets the color for every possible component in the main container
	 * @param container The container for the JFrame
	 */
	public void setGlobalColor(Container container) {
	    Border raisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	    Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		
	    for(Component c : container.getComponents())
	    {
	    	c.setForeground(foregroundColor);
            c.setBackground(backgroundColor);
	    	
	        if(c instanceof Container)
	        {
	        	if(c instanceof JPanel) {
	        		JPanel panel = (JPanel)c;
	        		panel.setBorder(loweredBorder);
	        	}
	        	
	        	if(c instanceof JTextField) {
	        		JTextField label = (JTextField)c;
	        		label.setBorder(loweredBorder);
	        	}
	        	
	        	if(c instanceof JButton) {
	        		JButton button = (JButton)c;
	        		System.out.println(button.getInsets());
	        		button.setBorder(new CompoundBorder(raisedBorder, new EmptyBorder(5, 17, 5, 17)));
	        		System.out.println(button.getInsets());
	        		
	        		pack();
	        	}
	        	
	        	if(c instanceof JTable) {
	        		JTable table = (JTable)c;
	        		table.setGridColor(new Color(80, 80, 80));
	        		table.setBorder(loweredBorder);
	        	}
	        	
                setGlobalColor((Container)c);
	        }
	    }
	}
	
	public void ShowManager(boolean show) {
		managing = show;
		
		getContentPane().remove(show ? resultsPanel : filePanel);
		getContentPane().add(show ? filePanel : resultsPanel, BorderLayout.CENTER);
		
		managerButton.setText(show ? "Search Results" : "File Manager");
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
