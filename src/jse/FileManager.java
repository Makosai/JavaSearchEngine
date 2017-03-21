package jse;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jse.Data.FileData;

/**
 * @author Team Javanonymous (Eric Pigott, Yosvany Reina, Kristopher Ali, Marcos Mendoza)
 *
 */
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text Files", "txt");
		fileBrowser.addChoosableFileFilter(filter);
		
		int choice = fileBrowser.showOpenDialog(null); // The user's input: cancel, approve, error
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			File file = fileBrowser.getSelectedFile();
			
			Populate(data, file, model);
		}
	}
	
	public static void Populate(Data data, File file, DefaultTableModel model) {
		String fileName = file.getName();
		FileData fileData = data.new FileData(
				fileName,
				"checksum",
				"data",
				file.getAbsolutePath()
		);
		data.files.add(fileData);
			
		model.addRow(new Object[] {fileData.name, Data.CheckStatus(fileData.checksum, fileData.path)});
	}
	
	/**
	 * Remove a file from the index.
	 * @param tableItem The row in which the file is located.
	 */
	public static void Remove(Data data, int[] rows, DefaultTableModel model) {
		for(int row : rows) {
			model.removeRow(row);
			data.files.remove(row);
		}
	}

}
