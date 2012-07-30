/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Dictionary.*;
import IO.DictionaryReader;
import utils.SimStringWrapper;

/**
 *
 * @author David Przybilla
 */
public class LookUpTest {
    
    
            public static double testMappedHashTable(int ngramsize){
                //specifies the number of n-grams : 3
                 //the output file of the dictionary: dbDav.test
                 LowLevelHashMappedDictionary2 dimp=new LowLevelHashMappedDictionary2(ngramsize,"/home/attickid/NetBeansProjects/simstring/src/utils/per.db");
                 
                 //wrapping the lowlevel implementation
                 DictionaryGenerator d1=new DictionaryImplementation(dimp);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                DictionaryReader.createDictionaryFromCorpus("/home/attickid/NetBeansProjects/simstring/src/utils/PER",ngramsize,d1);
                 //saves the dictionary into memeory
                 dimp.commit();
       
                 
                 //RandomTest mappedHashTableTest=new RandomTest("mapped hashtable",d1);
                  TestCONLLText mappedHashTableTest=new  TestCONLLText("mapped hashtable",d1);
                 
                 TestAssesor suffixTreeAssessor=new TestAssesor(mappedHashTableTest,1);
                return  suffixTreeAssessor.run();
            }
    
            public static double testSuffixTrees(int ngramsize){
            
            //specifies the number of n-grams : 3
                 //the output file of the dictionary: dbDav.test
                 LowLevelSuffixTreeImplementation dimp=new LowLevelSuffixTreeImplementation(ngramsize);
                 
                 //wrapping the lowlevel implementation
                 
                 DictionaryGenerator d1=new DictionaryImplementation(dimp);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                 DictionaryReader.createDictionaryFromCorpus("/home/attickid/NetBeansProjects/simstring/src/utils/PER",ngramsize,d1);
                 
                // RandomTest suffixTreeTest=new RandomTest("suffixTreetest",d1);
                 TestCONLLText  suffixTreeTest=new  TestCONLLText("suffixTreetest",d1);
                 
                 TestAssesor suffixTreeAssessor=new TestAssesor(suffixTreeTest,1);
                return  suffixTreeAssessor.run();
                 
                 
            }
    
    
             public static void main(String[] args){
                  
                 
                 //mapped hash table
                //double averageMappedHashTables=  testMappedHashTable(3); 
                 
                 //test for suffixtrees
                //double averageSuffixTrees= testSuffixTrees(3);
                 
                 //hash table
               // double averageHashTables= testHashtables(3); 
                
                //SimString test
                double averageSimString=testSimString(3);
                
                 
                
                
               // System.out.println("average suffix trees:"+ averageSuffixTrees);
                //System.out.println("average hash table:"+ averageHashTables);
                //System.out.println("average mapped hash table:"+ averageMappedHashTables);
                System.out.println("simString: "+ averageSimString);
                 
             }

    private static double testHashtables(int ngramsize) {
                      //specifies the number of n-grams : 3
                 
                LowLevelHashDictionary dimp2=new LowLevelHashDictionary(ngramsize);
                 
                 //wrapping the lowlevel implementation
                 
                 DictionaryGenerator d2=new DictionaryImplementation(dimp2);
                 
                 //tells where is the dictionary that one wants to process
                 //the number of n-grams 
                 //the dictonayImplementation that is incharged of making it
                 DictionaryReader.createDictionaryFromCorpus("/home/attickid/NetBeansProjects/simstring/src/utils/PER",ngramsize,d2);
                  //RandomTest hashTableTest=new RandomTest("hashTableTest",d2);
                  TestCONLLText hashTableTest=new  TestCONLLText("hashTableTest",d2);
                 
                 TestAssesor hashTableAssessor=new TestAssesor(hashTableTest,1);
                return hashTableAssessor.run();
    }

    private static double testSimString(int i) {
        SimStringWrapper.constructDictionary("/home/attickid/NetBeansProjects/simstring/src/utils/PER", "/home/attickid/NetBeansProjects/simstring/src/utils/PER.db", true);
        String dictionaryPath="/home/attickid/NetBeansProjects/simstring/src/utils/PER.db";
       // TestCONLLText simStringTest=new  TestCONLLText("hashTableTest",dictionaryPath);
        
        RandomTest simStringTest=new  RandomTest("hashTableTest",dictionaryPath);
         TestAssesor simStringAssessor=new TestAssesor(simStringTest,200);
                return simStringAssessor.run();
    }
                 
    
}
