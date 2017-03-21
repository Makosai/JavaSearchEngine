package jse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Team Javanonymous (Eric Pigott, Yosvany Reina, Kristopher Ali, Marcos Mendoza)
 *
 */
// class to return checksum of file.
public class Checksum {

	public static String GetChecksum(String path) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(Files.readAllBytes(Paths.get(path)));
		byte[] digest = md.digest();
		return Arrays.toString(digest);
	}
}
        