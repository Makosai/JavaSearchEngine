/**
 * 
 */
package jse;

import java.util.List;

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
		// Saves from the saving method that epigott is working on.
	}
	
	/**
	 * Grabs persistent data from a file, converting it from JSON to a List.
	 * If the file does not exist, the list is initialized but empty.
	 */
	public void Load() {
		// Loads from the loading method that epigott is working on.
	}

}
