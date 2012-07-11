/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.util.*;
import utils.Ngram;

/**
 *
 * This is a Naive implementation of a dictionary.
 * It simply holds the inverted index in memory on hashtables.
 * very fast but can't scalate
 * 
 * Keys in this index are of the shape "ngram-sizeOfy"
 * @author David Przybilla
 */
public class DictionaryImplementation implements DictionaryGenerator {
    
      LowLevelDictionaryImplementation imp;
    //size of ngrams
    int ngramSize;
    
    
    
    public DictionaryImplementation(LowLevelDictionaryImplementation imp){
     this.ngramSize=imp.getNgramSize();
     this.imp=imp;
     
    }

    /*
     * adds a word to the dictionary
     * @param word the word to be added to the dicitonary
     */
    @Override
    public void addTerm(String word) {
        
        //preprocess the word to add extra characters at the end and beggining
        word=Ngram.preprocessTerm(word, ngramSize);
        
        //split word into list of ngrams
        ArrayList<String> listOfNgrams=Ngram.splitIntoNGrams(word, ngramSize);
        
      
        
        //check the second hashtable, if the key does not exist create it
        
        for(int i=0;i<listOfNgrams.size();i++){
            
           String key=listOfNgrams.get(i)+"-"+Ngram.getNumberOfNgrams(word,this.ngramSize); 
           if(!imp.containsNgramSize(key)){
               PriorityQueue<Integer> list=new PriorityQueue<Integer>();
               imp.putEntity(key,list);
           }
           
           imp.putTerm(word);
           
           
           PriorityQueue<Integer> p=imp.getEntities(key);
          
           int id_temp=imp.getId(word);
           
//            System.out.println(id_temp);
           p.add((Integer)(id_temp));
           
           imp.putEntity(key, p);
        }
    }

     /*
     * search for the list of Ids of entities who has a given ngram and  have an specific size
     * @param size size of the entities that we are looking for
     * @param ngram ngram which are contained in the entities
     * @return list of ORDERED ids of entities
     */
    @Override
    public ArrayList<Integer> searchTerm(int size, String ngram) {
        
        ArrayList<Integer>  listOfIds=new ArrayList<Integer>();
        String key=ngram+"-"+size; 
        
        if(imp.containsNgramSize(key)){
            
            PriorityQueue<Integer> p=this.imp.getEntities(key);
            List<Integer> integers = new LinkedList<Integer>(p);
            listOfIds=new ArrayList<Integer>(integers);
           
        }
       
       //just debugging 
       for (int i=0;i<listOfIds.size();i++){
          // System.err.println(this.mapOfIds.get(listOfIds.get(i)));
       }
       
       return listOfIds;
    }
    
    /*
     * given the id of a word(entity) returns its string representations
     * @param id size of the entities that we are looking for
     * @return string representation of Entity with id(id)
     */
    public String getTerm(int id){
       
        return imp.getTerm(id);
    }

     /*
     * returns the ngramSize on which the dictionary was constructed
     */
    @Override
    public int getNgramSize() {
        return imp.getNgramSize();
    }
    
    
    
}
