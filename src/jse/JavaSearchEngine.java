package jse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class JavaSearchEngine extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;

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
	 * Create the frame.
	 */
	public JavaSearchEngine() {
		setResizable(false);
		setTitle("Search Engine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 51, 51));
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setBorderPainted(true);
		mnFile.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(102, 102, 102), new Color(102, 102, 102), new Color(0, 0, 0), new Color(0, 0, 0)));
		mnFile.setForeground(new Color(255, 255, 255));
		mnFile.setBackground(new Color(255, 255, 255));
		menuBar.add(mnFile);
		
		JMenuItem mntmAddFile = new JMenuItem("Add File(s)");
		mntmAddFile.setBorderPainted(true);
		mntmAddFile.setForeground(new Color(0, 0, 0));
		mntmAddFile.setBackground(new Color(255, 255, 255));
		mnFile.add(mntmAddFile);
		
		JMenuItem mntmRemoveFile = new JMenuItem("Remove File(s)");
		mntmRemoveFile.setBorderPainted(true);
		mntmRemoveFile.setForeground(new Color(0, 0, 0));
		mntmRemoveFile.setBackground(new Color(255, 255, 255));
		mnFile.add(mntmRemoveFile);
		
		JMenuItem mntmRefreshFiles = new JMenuItem("Refresh File(s)");
		mnFile.add(mntmRefreshFiles);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setBounds(65, 40, 360, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnAanyTerms = new JRadioButton("Any Terms");
		rdbtnAanyTerms.setForeground(new Color(255, 255, 255));
		rdbtnAanyTerms.setBackground(new Color(51, 51, 51));
		buttonGroup.add(rdbtnAanyTerms);
		rdbtnAanyTerms.setBounds(94, 86, 109, 23);
		contentPane.add(rdbtnAanyTerms);
		
		JRadioButton rdbtnExactPhrase = new JRadioButton("Exact Phrase");
		rdbtnExactPhrase.setForeground(new Color(255, 255, 255));
		rdbtnExactPhrase.setBackground(new Color(51, 51, 51));
		buttonGroup.add(rdbtnExactPhrase);
		rdbtnExactPhrase.setBounds(316, 86, 109, 23);
		contentPane.add(rdbtnExactPhrase);
		
		JRadioButton rdbtnAllTerms = new JRadioButton("All Terms");
		rdbtnAllTerms.setForeground(new Color(255, 255, 255));
		rdbtnAllTerms.setBackground(new Color(51, 51, 51));
		buttonGroup.add(rdbtnAllTerms);
		rdbtnAllTerms.setBounds(205, 86, 109, 23);
		contentPane.add(rdbtnAllTerms);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(51, 51, 51));
		btnSearch.setBounds(471, 39, 89, 23);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBackground(new Color(51, 51, 51));
		scrollPane.setBounds(10, 136, 624, 202);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setGridColor(new Color(255, 255, 255));
		table.setBackground(new Color(51, 51, 51));
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setName("Files\r\n");
		table.setModel(new DefaultTableModel(
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
				"Files", "Current"
			}
		));
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
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
