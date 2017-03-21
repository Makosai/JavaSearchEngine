/**
 * 
 */
package jse;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import java.io.*;



/**
 * @author Team Javanonymous (Eric Pigott, Yosvany Reina, Kristopher Ali, Marcos Mendoza)
 *
 */
public class Data {

	public Data() {
		//Load();
	}
	
	///////////
	// Variables
	///////////
	public List<FileData> files = new ArrayList<FileData>();
	
	///////////
	// Classes
	///////////
	/**
	 * Contains the information for added files.
	 */
	public class FileData {
		/**
		 * The name of the file.
		 */
		public String name;
		
		/**
		 * The last generated checksum of the file.
		 */
		public String checksum;
		
		/**
		 * The contents of the file loaded into RAM.
		 * (This may be removed, depending if it is better to open each file individually when searching) 
		 */
		public String data;

		/**
		 * The path of the file.
		 */
		public String path;
		
		public String status;
		
		public FileData(String name, String checksum, String data, String path) {
			this.name = name;
			this.checksum = checksum;
			this.data = data;
			this.path = path;
			this.status = "up to date";
		}
	}
	
	///////////
	// Methods
	///////////
	/**
	 * Saves the data to a file in a JSON format.
	 */
	public void Save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("data/FileData.dat");
			ObjectOutputStream saveOut = new ObjectOutputStream(fileOut);
			saveOut.writeObject(files);
			saveOut.close();
			fileOut.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Grabs persistent data from a file, converting it from JSON to a List.
	 * If the file does not exist, the list is initialized but empty.
	 */
	@SuppressWarnings("unchecked")
	public void Load(DefaultTableModel model) {
		try {
			FileInputStream fileIn = new FileInputStream("data/FileData.dat");
			ObjectInputStream saveIn = new ObjectInputStream(fileIn);
			files = (List<FileData>)saveIn.readObject();
			saveIn.close();
			fileIn.close();

			for(FileData fData : files) {
				FileManager.Populate(this, new File(fData.path), model);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
			return;
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}
	
	public void UpdateFiles() {
		
	}

	public static void FilesToTable(List<FileData> files, DefaultTableModel model) {
		for(FileData fileData : files) {
			model.addRow(new Object[] {fileData.name, CheckStatus(fileData.checksum, fileData.path)});
		}
	}

	public static String CheckStatus(String checksum, String path) {
		// TODO Auto-generated method stub
		return "UNKNOWN";
	}

}
