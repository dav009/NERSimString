/*
 * This is just an example class for the users of the library.
 * This example shows how to create and query a dictionary created 
 * on a mapped hash table
 * 
 *  * This is a data structure is stored in disk
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
            
            /* CREATING AND QUERYING A DICTIONARY */
            
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
           
        
            /* LOADING AND QUERYING A DICTIONARY */    
               System.out.println("----------------Loading dictionary---------------");
                 LowLevelHashMappedDictionary2 dimp2= LowLevelHashMappedDictionary2.load("/home/attickid/testOutput/dbDav.test",3);
                 DictionaryGenerator d2=new DictionaryImplementation(dimp2);
                 SimString.search(d2, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 0.8), "samen Adv O");
           
                
        }
    
}
