/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dictionary.DictionaryGenerator;
import Measures.MeasureFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import simstring.SimString;
import utils.FileManagement;
import utils.SimStringWrapper;
import utils.SimilarityMeasure;

/**
 *This class reads each word of the conll text for NER and query it to the dictionary
 * @author David Przybilla
 */
public class TestCONLLText implements Test {
     //name of the test
    public String name;
    
    //dictionary
    public DictionaryGenerator d;
    
    public boolean simString;
    
    public String dictionaryPath;
    
    public TestCONLLText(String name,DictionaryGenerator d){
        this.name=name;
        this.d=d;
        simString=false;
    }
    //constructor for callin the test with simTRing
    public TestCONLLText(String name,String dictionaryPath){
        this.name=name;
        this.d=d;
        this.dictionaryPath=dictionaryPath;
        simString=true;
    }
    
    public  void run(){
           //randomly generated string
        //just 200 words
        
        int countOfWords=0;
           ArrayList<String> lines=FileManagement.readFile("/home/attickid/NetBeansProjects/simstring/src/utils/originalCorpus");
           for (String line : lines){
              String[] words= line.split(" ");
              if(countOfWords>200){
                  break;
              }
              for(String word : words){
                  countOfWords++;
                  
                         String query=word;
                         if(this.simString==false){
                             SimString.search(d, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 0.8), query);               
                         }
                         else{
                    try {
                        SimStringWrapper.search(query,dictionaryPath,0.8, SimilarityMeasure.COSINE);
                    } catch (IOException ex) {
                        Logger.getLogger(TestCONLLText.class.getName()).log(Level.SEVERE, null, ex);
                    }
                         }
                      if(countOfWords>200){
                      break;
                  }
              }
           }
  }
    

}
