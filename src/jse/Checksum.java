/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jse;

/**
 *
 * @author epigott
 */
// class to return checksum of file.
public class Checksum {
        
        // Internally used.
        static String filepath;
        
        // Something has to happen, right?
        public Checksum() {
                filepath = "/default.txt";
                }
        
        // The magic of the class goes here.
        public String getChecksum(String filepath) {
                
                String md5sum;
                
                return filepath;
                }
        
        // This class is currently intended for use by jse.Window at time of writing.
        // Later expansion is expected.
        // Anything present if viewing has or will be used for debugging.
        public static void main(String[] args) {
            Checksum testing = new Checksum();
            System.out.println(testing.getChecksum(filepath));
            }
        }
