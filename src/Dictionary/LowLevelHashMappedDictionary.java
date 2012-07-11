/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentMap;
import net.kotek.jdbm.*;

/**
 *
 * @author attickid
 */
public class LowLevelHashMappedDictionary implements LowLevelDictionaryImplementation {
    
    
    DB mapOfTerms_db; 
    DB mapOfIds_db;
    DB mapOfSizes_db;
    
    ConcurrentMap<String,Integer> mapOfTerms;
    ConcurrentMap<Integer,String> mapOfIds;
    ConcurrentMap<String,PriorityQueue<Integer>> mapOfSizes;
    
        //size of ngrams
    int ngramSize;

    //current maxID of a term
    private int id=0;
    
    private int transactionCounter=0;
    
    
   public LowLevelHashMappedDictionary(int ngramSize,String pathToDictionary){
        mapOfTerms_db=  DBMaker.openFile(pathToDictionary+"_terms").disableTransactions().make();
        mapOfIds_db=  DBMaker.openFile(pathToDictionary+"_ids").disableTransactions().make();
        mapOfSizes_db=  DBMaker.openFile(pathToDictionary+"_sizes").disableTransactions().make();
        
        
          
        
        mapOfTerms =  mapOfTerms_db.createHashMap("termMap");
        mapOfIds =  mapOfIds_db.createHashMap("idMap");
        mapOfSizes =  mapOfSizes_db.createHashMap("sizeMap");
        
        this.ngramSize=ngramSize;
        
       

   }

        

    /* given an id of a term returns its String representation 
    * @param id term id
    */
    public String getTerm(int id){
        if(mapOfIds.containsKey(id))
                return mapOfIds.get(id);
        else
            return "";
        
    }

    /* given a term returns its id
    * @param term the term from which the id will be returned
    */
    public int getId(String term){
        Integer id=mapOfTerms.get(term);
        if(mapOfTerms.containsKey(term) && id!=null){
            return id;
    }
    
        else{
           return -1;
        }

    }

    /* returns the ngramSize
    */
    public int getNgramSize(){
        return this.ngramSize;
    }

    /* 
     * states that the id for word is wordId
     * @param wordId id of the word
     * @param word word which will be liked to id wordID
    */
    public void putTerm(String word){
     if(!mapOfTerms.containsKey(word)){
         mapOfTerms.put(word,id);
         mapOfIds.put(id,word);
         id++;
         
         transactionCounter++;
         
     }   
     if(transactionCounter>30){
      //   mapOfTerms_db.commit();
        // mapOfIds_db.commit();
         transactionCounter=0;
     }
        
        
        
    }

    /* states that the list Of entities which contains the ngram-size is in p.
     * @param ngram_size the key for which the list p can be searched "ngram-sizeOfStringsInP"
     * @param p list of ordered ids of the entities which contain ngram "ngram" and are of size "size"
    */
    public void putEntity(String ngram_size,PriorityQueue<Integer> p){
        this.mapOfSizes.put(ngram_size,p);
        transactionCounter++;
         if(transactionCounter>30){
         //mapOfSizes_db.commit();
         transactionCounter=0;
     }
        
    }

    /*
    * returns the list of entities which have an ngram "ngram" and the entities are of size "size"
    */
    public PriorityQueue<Integer> getEntities(String ngram_size){
        
        
        PriorityQueue<Integer> p=this.mapOfSizes.get(ngram_size);
        if(this.mapOfSizes.containsKey(ngram_size) && p!=null && p.size()>0)
            return p;
        
        else
            return new PriorityQueue<Integer>();
    }

    /*
    * returns whether a key with a shape "ngram-sizeOfY" is in the dict
    */
    public boolean containsNgramSize(String ngram_size){
        return mapOfSizes.containsKey(ngram_size);
    }

    /*
     * returns whether the string Word is in the list of terms (has an id)
     */
    public boolean containsTerm(String word)
    {
        return mapOfTerms.containsKey(word);
    }


}
