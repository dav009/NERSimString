/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simstring;

import Dictionary.*;
import IO.DictionaryReader;
import Measures.MeasureFactory;
import utils.Ngram;
import utils.SimStringWrapper;
import utils.SimilarityMeasure;

import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author attickid
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
       
        // TODO code application logic here
        Ngram.splitIntoNGrams("methyl sulphone", 3);
        
        //creating
        LowLevelHashMappedDictionary2 dimp=new LowLevelHashMappedDictionary2(3,"/home/attickid/dbDav.test");
        DictionaryGenerator d1=new DictionaryImplementation(dimp);
        
        
        
        //loading
        //  DictionaryGenerator d1=new DictionaryImplementation(new LowLevelHashMappedDictionary2("/home/attickid/dbDav.test",3));
      
        
        //DictionaryGenerator d1=new DictionaryImplementation(new LowLevelHashDictionary(3));
        //DictionaryGenerator d1=new DictionaryImplementation(new LowLevelSuffixTreeImplementation(3));

      //  DictionaryGenerator d2=new VoldemortDictionary(3,"tcp://localhost:6666","dict1");
        DictionaryReader.createDictionaryFromCorpus("/home/attickid/ned.train",3,d1);
        dimp.commit();
       
        long start = System.currentTimeMillis();

       // for (int i = 0; i < 5000; i++)
        //{
            SimString.search(d1, MeasureFactory.create(MeasureFactory.COSINE_SIMILARITY, 1.0), "samen Adv O");
        //}

        long end = System.currentTimeMillis();
        System.out.println("Execution time was "+(end-start)+" ms.");
        //163 key
      //  d1.getTerm(30);
       // SimString.search(d1, MeasureFactory.create(MeasureFactory.DICE_SIMILARITY, 0.8), "winnen");
     //   SimString.search(d1, MeasureFactory.create(MeasureFactory.JACCARD_SIMILARIY, 0.8), "winnen");
        
        
        
    }
}
