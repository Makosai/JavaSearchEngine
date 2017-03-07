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
		/**
		 * Class to serialize the file list in order to save and load.
		 */
		public class SerializeData{
			/**
			 * try block for the serialization
			 */
			public void Saving(){
				try {
					FileOutputStream fileOut = new FileOutputStream("tmp/SavesForEngine.ser");
					ObjectOutputStream saveOut = new ObjectOutputStream(fileOut);
					saveOut.writeObject(files);
					saveOut.close();
					fileOut.close();
				}
				catch(IOException e ){
				//do something when file not found.
			}
		}
	}
	}
	
	///////////
	// Methods
	///////////
	/**
	 * Saves the data to a file in a JSON format.
	 */
	public void Save() {
		// Saves from the saving method that epigott is working on.
	}
	
	/**
	 * Grabs persistent data from a file, converting it from JSON to a List.
	 * If the file does not exist, the list is initialized but empty.
	 */
	public void Load() {
		// Loads from the loading method that epigott is working on.
		/**
		 *  Im going to put the loading of the files in here since it only makes sense to. I will surround them
		 *  in a comment block since i don't know if it will conflict with Erics loading code and or if it is even needed.
		 *  - Marcos
		 */
		/**
		 * Data a = null;
		 * try {
		 * 	FileInputStream fileIn = new FileInputStream("/tmp/SavesForEngine.ser");
		 * 	ObjectInputStream saveIn = new OBjectInputStream(fileIn);
		 * 	a = (Data) saveIn.readObject();
		 * 	saveIn.close();
		 * 	fileIn.close();
		 * }
		 * catch(IOException b ) {
		 * 	b.printStackTrace();
		 * 	return;
		 * }
		 * catch(ClassNotFoundException c) {
		 * 	System.out.println("Employee class not found");
		 * 	c.printStackTrace();
		 * 	return;
		 * }
		 * 
		 */
	}

}
