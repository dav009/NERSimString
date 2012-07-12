/*
 * This is just an example class for the users of the library.
 * This example shows how to create and query a dictionary created 
 * on a trie mapped structures
 * 
 * This is a data structure hold in memory
 */
package Examples;

import Dictionary.DictionaryGenerator;
import Dictionary.DictionaryImplementation;
import Dictionary.LowLevelSuffixTreeImplementation;
import IO.DictionaryReader;
import Measures.MeasureFactory;
import simstring.SimString;

/**
 *
 * @author David Przybilla
 */
public class MappedSuffixTreeExample {
    
        public static void main(String[] args){
                 //specifies the number of n-grams : 3
                 //the output file of the dictionary: dbDav.test
                 LowLevelSuffixTreeImplementation dimp=new LowLevelSuffixTreeImplementation(3);
                 
                 //wrapping the lowlevel implementation
                 
                 DictionaryGenerator d1=new DictionaryImplementation(dimp);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                 DictionaryReader.createDictionaryFromCorpus("/home/attickid/canadianActors",3,d1);
                
                 long totalTime=0;
                 for(int i=0;i<30;i++){
                    long start = System.currentTimeMillis();

                    //calls the search algorithm 
                    //it takes as an input the dictionaryImplemntation
                    //the disered mesaure
                    // the query
                    SimString.search(d1, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 0.2), "Chandra West");
                    long end = System.currentTimeMillis();
                     totalTime+=end-start;
                 }
                 System.out.println("Execution time was "+(totalTime)+" ms.");
                
    }
    
}
