/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author David Przybilla
 */
public class FileManagement {
    	//returns a String containing n numberOfLines starting after the line firstLine found in the file pathFile
	        public static ArrayList<String> readFile(String pathFile){

				 ArrayList<String> lines=new  ArrayList<String>();
				 try{
				  
						  FileInputStream fstream = new FileInputStream(pathFile);
						  
						  DataInputStream in = new DataInputStream(fstream);
						  BufferedReader br = new BufferedReader(new InputStreamReader(in));
						  String strLine;
						  int lineNumber=0;
						
						  while ( ((strLine = br.readLine()) != null)  ) {
						  
						  	
								lines.add(strLine);
							
							lineNumber++;
						  }
						  
						  in.close();
				    }catch (Exception e){
				  	System.err.println("Error: " + e.getMessage());
				  }
				return lines;
				
		}
}
