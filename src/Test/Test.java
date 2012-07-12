/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dictionary.DictionaryGenerator;
import Measures.MeasureFactory;
import org.apache.commons.lang3.RandomStringUtils;
import simstring.SimString;

/**
 *
 * @author David Przybilla
 */
 class Test {
    
    //name of the test
    public String name;
    
    //dictionary
    public DictionaryGenerator d;
    
    public Test(String name,DictionaryGenerator d){
        this.name=name;
        this.d=d;
    }
    
    public  void run(){
           //randomly generated string
           String query="";
           query=RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3)+" "+RandomStringUtils.randomAlphabetic(3);
           SimString.search(d, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 0.2), query);               
    }
    

   
    
}
