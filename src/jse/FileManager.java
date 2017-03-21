package jse;

import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;


public class FileManager { 
	
	/**
	 * Brings up a file browser to extract it's name and current status.
	 * @param fName The name of the file.
	 * @param status Checks if the file is current.
	 */
	public static void Add(String fName, String status) {
		
		JFileChooser FBrowser = new JFileChooser();
		
		int returnVal = FBrowser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			fName = FBrowser.getSelectedFile().getName();
			// As of now this will display only the date the filled was last modified
			// and not it's status
			Date d = new Date(FBrowser.getSelectedFile().lastModified());	
			status = d.toString();
			System.out.println(fName + " " + status);
			
			//TODO Add functionality to pass to List
		}

	}
	
	/**
	 * Remove a file from the index.
	 * @param tableItem The row in which the file is located.
	 */
	@SuppressWarnings("null")
	public static void Remove(int[] tableItem) {
		List<Window> tempList = null;
		tempList.remove(tableItem);
		// Once the actual list gets going I'll be able to replace the List
		// line with proper list and test removal functionality from there
		
		// TODO Add functionality to Remove File button
		
	}

}
