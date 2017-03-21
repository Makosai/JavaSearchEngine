/**
 * 
 */
package jse;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;



/**
 * @author Team Javanonymous (Eric Pigott, Yosvany Reina, Kristopher Ali, Marcos Mendoza)
 *
 */
public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 316392703472625828L;

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
	public class FileData implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2886330116245026520L;

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
		
		public FileData(String name, String checksum, String data, String path, String status) {
			this.name = name;
			this.checksum = checksum;
			this.data = data;
			this.path = path;
			this.status = status;
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
			FileOutputStream fileOut = new FileOutputStream("data/FileData.ser");
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
			FileInputStream fileIn = new FileInputStream("data/FileData.ser");
			ObjectInputStream saveIn = new ObjectInputStream(fileIn);
			files = (List<FileData>)saveIn.readObject();
			saveIn.close();
			fileIn.close();

			for(FileData fData : files) {
				fData.status = CheckStatus(fData.checksum, fData.path);
				model.addRow(new Object[] {fData.name, fData.status});
			}
		}
		catch(IOException e) {
			//e.printStackTrace();
			return;
		}
		catch(ClassNotFoundException c) {
			//c.printStackTrace();
			return;
		}
	}
	
	public void UpdateFiles(Data data, int[] rows, DefaultTableModel model) {
		for(int row : rows) {
			FileData fData = files.get(row);
			fData.data = ReadFile(fData.path);
			try {
				fData.checksum = Checksum.GetChecksum(fData.path);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			fData.status = CheckStatus(fData.checksum, fData.path);
			model.setValueAt(fData.status, row, 1);
		}
		data.Save();
	}

	public static void FilesToTable(List<FileData> files, DefaultTableModel model) {
		for(FileData fileData : files) {
			model.addRow(new Object[] {fileData.name, CheckStatus(fileData.checksum, fileData.path)});
		}
	}

	public static String CheckStatus(String checksum, String path) {
		try {
			String pathValue = Checksum.GetChecksum(path);
			if(pathValue.equals(null)) {
				return "missing";
			}
			if(pathValue.equals(checksum)) {
				return "up to date";
			}
			if(!pathValue.equals(checksum)) {
				return "modified";
			}
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		
		return "error";
	}
	
	public static String ReadFile(String path) 
	{
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded, Charset.defaultCharset());
	}

}
