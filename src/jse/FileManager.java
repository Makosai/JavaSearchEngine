package jse;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jse.Data.FileData;


public class FileManager { 
	
	/**
	 * Brings up a file browser to extract it's name and current status.
	 * @param fName The name of the file.
	 * @param status Checks if the file is current.
	 */
	public static void Add(Data data, DefaultTableModel model) {
		
		// Brings up window to choose files
		JFileChooser fileBrowser = new JFileChooser();
		
		// Allows user to filter non-Text file types
		FileNameExtensionFilter TextOnlyFilter = new FileNameExtensionFilter(
				"Text Files", "txt");
		fileBrowser.addChoosableFileFilter(TextOnlyFilter);
		
		int choice = fileBrowser.showOpenDialog(null); // The user's input: cancel, approve, error
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileBrowser.getSelectedFile();
			String fileName = selectedFile.getName();
			FileData fileData = data.new FileData(
					fileName,
					"checksum",
					"data",
					selectedFile.getAbsolutePath()
			);
			data.files.add(fileData);
				
			model.addRow(new Object[] {fileData.name, Data.CheckStatus(fileData.checksum, fileData.path)});
		}
	}
	
	/**
	 * Remove a file from the index.
	 * @param tableItem The row in which the file is located.
	 */
	@SuppressWarnings("null")
	public static void Remove(int row, DefaultTableModel model) {
		model.removeRow(row);		
	}

}
