package jse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class JavaSearchEngine extends JFrame {

	private JPanel windowPane;
	private JTextField searchBar;
	private final ButtonGroup radioButtonGroup = new ButtonGroup();
	private JTable fileList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaSearchEngine frame = new JavaSearchEngine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the window and contents.
	 */
	public JavaSearchEngine() {
		setResizable(false);
		setTitle("Search Engine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		
		JMenuBar searchEngineMenuBar = new JMenuBar();
		searchEngineMenuBar.setBorderPainted(false);
		searchEngineMenuBar.setBackground(new Color(51, 51, 51));
		setJMenuBar(searchEngineMenuBar);
		
		JMenu menuFile = new JMenu("File");
		menuFile.setBorderPainted(true);
		menuFile.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(102, 102, 102), new Color(102, 102, 102), new Color(0, 0, 0), new Color(0, 0, 0)));
		menuFile.setForeground(new Color(255, 255, 255));
		menuFile.setBackground(new Color(255, 255, 255));
		searchEngineMenuBar.add(menuFile);
		
		JMenuItem subMenuAddFile = new JMenuItem("Add File(s)");
		subMenuAddFile.setBorderPainted(true);
		subMenuAddFile.setForeground(new Color(0, 0, 0));
		subMenuAddFile.setBackground(new Color(255, 255, 255));
		menuFile.add(subMenuAddFile);
		
		JMenuItem subMenuRemoveFile = new JMenuItem("Remove File(s)");
		subMenuRemoveFile.setBorderPainted(true);
		subMenuRemoveFile.setForeground(new Color(0, 0, 0));
		subMenuRemoveFile.setBackground(new Color(255, 255, 255));
		menuFile.add(subMenuRemoveFile);
		
		JMenuItem subMenuRefreshFile = new JMenuItem("Refresh File(s)");
		menuFile.add(subMenuRefreshFile);
		
		windowPane = new JPanel();
		windowPane.setBackground(new Color(51, 51, 51));
		windowPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(windowPane);
		windowPane.setLayout(null);
		
		searchBar = new JTextField();
		searchBar.setText("Please enter search terms...");
		searchBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchBar.setBounds(65, 40, 360, 20);
		windowPane.add(searchBar);
		searchBar.setColumns(10);
		
		JRadioButton radioAnyTerms = new JRadioButton("Any Terms");
		radioAnyTerms.setForeground(new Color(255, 255, 255));
		radioAnyTerms.setBackground(new Color(51, 51, 51));
		radioButtonGroup.add(radioAnyTerms);
		radioAnyTerms.setBounds(94, 86, 109, 23);
		windowPane.add(radioAnyTerms);
		
		JRadioButton radioExactPhrase = new JRadioButton("Exact Phrase");
		radioExactPhrase.setForeground(new Color(255, 255, 255));
		radioExactPhrase.setBackground(new Color(51, 51, 51));
		radioButtonGroup.add(radioExactPhrase);
		radioExactPhrase.setBounds(316, 86, 109, 23);
		windowPane.add(radioExactPhrase);
		
		JRadioButton radioAllTerms = new JRadioButton("All Terms");
		radioAllTerms.setForeground(new Color(255, 255, 255));
		radioAllTerms.setBackground(new Color(51, 51, 51));
		radioButtonGroup.add(radioAllTerms);
		radioAllTerms.setBounds(205, 86, 109, 23);
		windowPane.add(radioAllTerms);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(new Color(51, 51, 51));
		searchBtn.setBounds(471, 39, 89, 23);
		windowPane.add(searchBtn);
		
		JScrollPane fileListScrollPane = new JScrollPane();
		fileListScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fileListScrollPane.setBackground(new Color(51, 51, 51));
		fileListScrollPane.setBounds(10, 136, 624, 202);
		windowPane.add(fileListScrollPane);
		
		fileList = new JTable();
		fileList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fileList.setGridColor(new Color(255, 255, 255));
		fileList.setBackground(new Color(51, 51, 51));
		fileListScrollPane.setViewportView(fileList);
		fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		fileList.setName("Files\r\n");
		fileList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Files", "Status"
			}
		));
	}
	private static void fileMenuPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
