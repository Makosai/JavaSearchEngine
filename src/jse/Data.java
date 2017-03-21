/**
 * 
 */
package jse;

import java.util.List;
import java.io.IOException;
import java.io.Serializable;
import java.io.*;



/**
 * @author Team Javanonymous (Eric Pigott, Yosvany Reina, Kristopher Ali, Marcos Mendoza)
 *
 */
public class Data {

	public Data() {
		Load();
	}
	
	///////////
	// Variables
	///////////
	public List<FileData> files;
	
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
		
		public FileData(String name, String checksum, String data) {
			this.name = name;
			this.checksum = checksum;
			this.data = data;
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
	public void Load() {
		try {
			FileInputStream fileIn = new FileInputStream("data/FileData.dat");
			ObjectInputStream saveIn = new ObjectInputStream(fileIn);
			files = (List<FileData>)saveIn.readObject();
			saveIn.close();
			fileIn.close();
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

}
