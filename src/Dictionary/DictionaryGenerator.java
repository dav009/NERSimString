/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.ArrayList;

/**
 *
 * Interface for allowing different implementations of Dictionary representations.
 * Different implementations of this interface should drastically change the search times.
 * A dictionary is a class that holds the inverted index of ngrams and it is able to make lookups in 
 * the inverted index.
 * 
 * 
 * A dictionary should give an id to each entity
 * A dictionary should have an ordered set of ids associated to an ngram.
 * @author David Przybilla
 */
public interface DictionaryGenerator {
    
    
    
    /*
     * adds a word to the dictionary
     * @param word the word to be added to the dicitonary
     */
    public void addTerm(String word);
    
   
     /*
     * search for the list of Ids of entities who has a given ngram and  have an specific size
     * @param size size of the entities that we are looking for
     * @param ngram ngram which are contained in the entities
     * @return list of ORDERED ids of entities
     */
    public ArrayList<Integer> searchTerm(int size, String ngram);
    
     /*
     * given the id of a word(entity) returns its string representations
     * @param id size of the entities that we are looking for
     * @return string representation of Entity with id(id)
     */
    public String getTerm(int id);
    
     /*
     * returns the ngramSize on which the dictionary was constructed
     */
    public int getNgramSize();
}
