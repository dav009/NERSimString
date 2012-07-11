package Dictionary;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * User: Boris Kozlov
 * Date: 16/06/12
 * Time: 14:06
 */
public class LowLevelHashDictionary implements LowLevelDictionaryImplementation
{

    //hashtable from size of strings to a hashmap of ngrams
    HashMap<String,PriorityQueue<Integer>> mapOfSizes;
    //given a string returns the id
    HashMap<String,Integer> mapOfTerms;
    //given an id returns the string
    HashMap<Integer,String> mapOfIds;

    //size of ngrams
    int ngramSize;

    //current maxID of a term
    private int id=0;
    
    
    public  LowLevelHashDictionary(int ngramSize){
        this.id=0;
        this.ngramSize=ngramSize;
        mapOfSizes=new HashMap<String,PriorityQueue<Integer>>();
        mapOfTerms=new HashMap<String,Integer> ();
        mapOfIds=new HashMap<Integer,String>();
    }


    /* given an id of a term returns its String representation 
    * @param id term id
    */
    public String getTerm(int id){
        if(mapOfIds.containsKey(id))
                return mapOfIds.get(id);
        else
            return null;
        
    }

    /* given a term returns its id
    * @param term the term from which the id will be returned
    */
    public int getId(String term){
        
       return mapOfTerms.get(term);
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
     }   
        
        
        
    }

    /* states that the list Of entities which contains the ngram-size is in p.
     * @param ngram_size the key for which the list p can be searched "ngram-sizeOfStringsInP"
     * @param p list of ordered ids of the entities which contain ngram "ngram" and are of size "size"
    */
    public void putEntity(String ngram_size,PriorityQueue<Integer> p){
        this.mapOfSizes.put(ngram_size,p);
        
    }

    /*
    * returns the list of entities which have an ngram "ngram" and the entities are of size "size"
    */
    public PriorityQueue<Integer> getEntities(String ngram_size){
        if(this.mapOfSizes.containsKey(ngram_size))
            return this.mapOfSizes.get(ngram_size);
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
