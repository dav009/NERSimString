/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dictionary.DictionaryGenerator;
import Measures.MeasureFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import simstring.SimString;
import utils.SimStringWrapper;
import utils.SimilarityMeasure;

/**
 *
 * @author David Przybilla
 */
 class RandomTest implements Test {
    
    //name of the test
    public String name;
    
    //dictionary
    public DictionaryGenerator d;
    
    public boolean simString;
    
    public String dictionaryPath;
    
    public RandomTest(String name,DictionaryGenerator d){
        this.name=name;
        this.d=d;
        simString=false;
    }
    
    public RandomTest(String name,String dictionaryPath){
        this.name=name;
        this.simString=true;
        this.dictionaryPath=dictionaryPath;
    }
    
    public  void run(){
           //randomly generated string
              String query="";
           query=RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3);
      
        if(simString==false){
          SimString.search(d, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 0.2), query);     
        }
        else{
            try {
                SimStringWrapper.search(query,dictionaryPath,0.2, SimilarityMeasure.COSINE);
            } catch (IOException ex) {
                Logger.getLogger(RandomTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

   
    
}
