/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.PriorityQueue;

/**
 *
 * @author attickid
 */
public interface LowLevelDictionaryImplementation {

    /* given an id of a term returns its String representation
     * @param id term id
     */
    public String getTerm(int id);
    
    /* given a term returns its id
     * @param term the term from which the id will be returned
     */
    public int getId(String term);
    
     /* returns the ngramSize
     */
    public int getNgramSize();
    
     /* 
      * states that the id for word is wordId
      * @param wordId id of the word
      * @param word word which will be liked to id wordID
     */
    public void putTerm(String word);
    
     /* states that the list Of entities which contains the ngram-size is in p.
      * @param ngram_size the key for which the list p can be searched "ngram-sizeOfStringsInP"
      * @param p list of ordered ids of the entities which contain ngram "ngram" and are of size "size"
     */
    public void putEntity(String ngram_size,PriorityQueue<Integer> p);
    
    /*
     * returns the list of entities which have an ngram "ngram" and the entities are of size "size"
     */
    public PriorityQueue<Integer> getEntities(String ngram_size);
    
    /*
     * returns whether a key with a shape "ngram-sizeOfY" is in the dict
     */
    public boolean containsNgramSize(String ngram_size);

    /*
     * returns whether the string Word is in the list of terms (has an id)
     */
    public boolean containsTerm(String word);
    
    
}
