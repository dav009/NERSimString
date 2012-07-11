/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author attickid
 */
public class suffixTreesDictionary implements DictionaryGenerator {
    
    
    LowLevelDictionaryImplementation lowLevelDictionary;
    
     //hashtable from size of strings to a hashmap of ngrams
    HashMap<String,PriorityQueue<Integer>> mapOfSizes;
    //given a string returns the id
    HashMap<String,Integer> mapOfTerms;
     //given an id returns the string
    HashMap<Integer,String> mapOfIds;
    
    //size of ngrams
    int ngramSize;
    
    //current maxID of a term
    int id=0;

    @Override
    public void addTerm(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Integer> searchTerm(int size, String ngram) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTerm(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getNgramSize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
