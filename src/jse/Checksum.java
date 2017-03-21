/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jse;

// imports needed for Checksum.java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author epigott
 */
// class to return checksum of file.
public class Checksum{
        
        // Internally used.
        static String
            filepath = "/default.txt",
            md5sum = "";
        
        // Something has to happen, right?
        public Checksum() {
                }
        
        // The magic of the class goes here.
        public String getChecksum(String filepath) throws Exception {
                
                int finc = 0;
                byte[] bufferIn = new byte[4096];
            
                
                try {
                    
                    // Determines existance of file to sum up.
                    File datafile = new File(filepath);
                    boolean exists = datafile.exists();
                    
                    // Sets up connection to file and 
                    MessageDigest md5buffer = MessageDigest.getInstance("MD5");
                    FileInputStream datafileStream = new FileInputStream(datafile);
                    
                    while ((finc = datafileStream.read(bufferIn)) != -1) {
                    md5buffer.update(bufferIn, 0, finc);
                    }
                    
                    byte[] bufferOut = md5buffer.digest();
                    
                    StringBuffer bufferString = new StringBuffer();
                    for (int i = 0; i < bufferOut.length; i++) {
                    bufferString.append(Integer.toString((bufferOut[i] & 0xff) + 0x100, 16).substring(1));
                        }
                    
                    md5sum = bufferString.toString();
                    // aSystem.out.println("Digest(in hex format):: " + bufferString.toString());
                    return md5sum;
                }
                catch(IOException | NoSuchAlgorithmException e) {
                    System.out.println(e.toString());
                    }
                return md5sum;
                }
        
        // This class is currently intended for use by jse.Window at time of writing.
        // Later expansion is expected.
        // Anything present if viewing has or will be used for debugging.
        public static void main(String[] args) throws Exception {
            Checksum testing = new Checksum();
            try {
                String testingString = testing.getChecksum(filepath);
                System.out.println(testingString);
            }
            catch(IOException | NoSuchAlgorithmException e) {
                System.out.println(e.toString());
            }
        }
    }