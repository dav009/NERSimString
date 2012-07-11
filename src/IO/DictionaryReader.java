/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.*;
import Dictionary.DictionaryGenerator;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dictionary.DictionaryGenerator;

/**
 *
 * Given a file, an ngram size and a dictionary Generator
 * this file reads a file line per line
 * and adds each line to the dictionary generator
 *
 * @author David Przybilla
 */
public class DictionaryReader{
    
     
    /*
     *@param path pathofthefile containing the entities entries 
     *@param ngramSize size of the ngram in which the index will be generatd
     *@param d a dictionary in which each of the entries will be added
     *@return returns d, filled with each of the entities given in the file located in path
     */
    public static DictionaryGenerator createDictionaryFromCorpus(String path,int ngramSize, DictionaryGenerator d){
        DictionaryGenerator dict;
        BufferedReader br = null;
        dict=d;
        
        try {
           
           //read each line
            br = new BufferedReader(new FileReader(path));
            try {
               
                String line ;

                while ((line = br.readLine()) != null) {
                    String preprocessedLine=line.trim();
                   // System.err.println("adding terms..");
                   //add each term to the dictionary
                    d.addTerm(preprocessedLine);
                    
                }
               
            } finally {
                //close the bufffer
                br.close();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DictionaryReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DictionaryReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
     return d;  
    }

}
