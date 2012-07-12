/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dictionary.DictionaryGenerator;
import Dictionary.DictionaryImplementation;
import Dictionary.LowLevelHashDictionary;
import Dictionary.LowLevelSuffixTreeImplementation;
import IO.DictionaryReader;

/**
 *
 * @author David Przybilla
 */
public class LookUpTest {
    
            public static double testSuffixTrees(){
            
            //specifies the number of n-grams : 3
                 //the output file of the dictionary: dbDav.test
                 LowLevelSuffixTreeImplementation dimp=new LowLevelSuffixTreeImplementation(3);
                 
                 //wrapping the lowlevel implementation
                 
                 DictionaryGenerator d1=new DictionaryImplementation(dimp);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                 DictionaryReader.createDictionaryFromCorpus("/home/attickid/canadianActors",3,d1);
                 
                 Test suffixTreeTest=new Test("suffixTreetest",d1);
                 
                 
                 TestAssesor suffixTreeAssessor=new TestAssesor(suffixTreeTest,200);
                return  suffixTreeAssessor.run();
                 
                 
            }
    
    
             public static void main(String[] args){
                  
                 //test for suffixtrees
                double averageSuffixTrees= testSuffixTrees();
                 
                 //hash table
                double averageHashTables= testHashtables(); 
                
                
                System.out.println("average suffix trees:"+ averageSuffixTrees);
                System.out.println("average hash table:"+ averageHashTables);
                 
             }

    private static double testHashtables() {
                      //specifies the number of n-grams : 3
                 
                LowLevelHashDictionary dimp2=new LowLevelHashDictionary(3);
                 
                 //wrapping the lowlevel implementation
                 
                 DictionaryGenerator d2=new DictionaryImplementation(dimp2);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                 DictionaryReader.createDictionaryFromCorpus("/home/attickid/canadianActors",3,d2);
                  Test hashTableTest=new Test("hashTableTest",d2);
                 
                 
                 TestAssesor hashTableAssessor=new TestAssesor(hashTableTest,200);
                return hashTableAssessor.run();
    }
                 
    
}
