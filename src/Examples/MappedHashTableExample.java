/*
 * This is just an example class for the users of the library.
 * This example shows how to create and query a dictionary created 
 * on a mapped hash table
 */
package Examples;

import Dictionary.DictionaryGenerator;
import Dictionary.DictionaryImplementation;
import Dictionary.LowLevelHashMappedDictionary2;
import IO.DictionaryReader;
import Measures.MeasureFactory;
import simstring.SimString;

/**
 *
 * @author David Przybilla
 */
public class MappedHashTableExample {
    
        public static void main(String[] args){
                 //specifies the number of n-grams : 3
                 //the output file of the dictionary: dbDav.test
                 LowLevelHashMappedDictionary2 dimp=new LowLevelHashMappedDictionary2(3,"/home/attickid/testOutput/dbDav.test");
                 
                 //wrapping the lowlevel implementation
                 DictionaryGenerator d1=new DictionaryImplementation(dimp);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                 DictionaryReader.createDictionaryFromCorpus("/home/attickid/ned.train2",3,d1);
                 //saves the dictionary into memeory
                 dimp.commit();
       
           
                //calls the search algorithm 
                //it takes as an input the dictionaryImplemntation
                //the disered mesaure
                // the query
                SimString.search(d1, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 0.8), "samen Adv O");
           
        }
    
}
