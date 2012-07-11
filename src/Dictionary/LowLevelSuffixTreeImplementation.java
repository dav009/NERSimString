package Dictionary;


import org.ardverk.collection.*;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * User: Boris Kozlov
 * Date: 16/06/12
 * Time: 14:38
 */
public class LowLevelSuffixTreeImplementation implements LowLevelDictionaryImplementation
{
    //given a string returns the id
    HashMap<String,Integer> mapOfTerms;

    //given an id returns the string
    HashMap<Integer,String> mapOfIds;

    //size of ngrams
    int ngramSize;

    //current maxID of a term
    private int id=0;

    // Suffix tree representation of the inverted index.
    Trie<String, PriorityQueue<Integer>> trie;

    public LowLevelSuffixTreeImplementation (int ngramSize)
    {
        this.id=0;
        this.ngramSize=ngramSize;
        trie = new PatriciaTrie<String, PriorityQueue<Integer>>(StringKeyAnalyzer.INSTANCE);
        mapOfTerms=new HashMap<String,Integer> ();
        mapOfIds=new HashMap<Integer,String>();
    }

    /* given an id of a term returns its String representation
    * @param id term id
    */
    public String getTerm(int id)
    {
        if (mapOfIds.containsKey(id))
            return mapOfIds.get(id);
        else
            return null;
    }

    /* given a term returns its id
    * @param term the term from which the id will be returned
    */
    public int getId(String term)
    {
        if (mapOfTerms.containsKey(term))
            return mapOfTerms.get(term);
        else
            return -1;
    }

    /* returns the ngramSize
    */
    public int getNgramSize()
    {
        return ngramSize;
    }

    /*
     * states that the id for word is wordId
     * @param wordId id of the word
     * @param word word which will be liked to id wordID
    */
    public void putTerm(String word)
    {
        if(!mapOfTerms.containsKey(word))
        {
            mapOfTerms.put(word,id);
            mapOfIds.put(id,word);
            id++;
        }
    }

    /* states that the list Of entities which contains the ngram-size is in p.
     * @param ngram_size the key for which the list p can be searched "ngram-sizeOfStringsInP"
     * @param p list of ordered ids of the entities which contain ngram "ngram" and are of size "size"
    */
    public void putEntity(String ngram_size,PriorityQueue<Integer> p)
    {
        trie.put(ngram_size, p);
    }

    /*
    * returns the list of entities which have an ngram "ngram" and the entities are of size "size"
    */
    public PriorityQueue<Integer> getEntities(String ngram_size)
    {
        if (trie.containsKey(ngram_size))
            return trie.get(ngram_size);
        else
            return new PriorityQueue<Integer>();
    }

    /*
    * returns whether a key with a shape "ngram-sizeOfY" is in the dict
    */
    public boolean containsNgramSize(String ngram_size)
    {
        return trie.containsKey(ngram_size);
    }

    /*
     * returns whether the string Word is in the list of terms (has an id)
     */
    public boolean containsTerm(String word)
    {
        return mapOfTerms.containsKey(word);
    }

}
