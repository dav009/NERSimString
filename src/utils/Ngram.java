/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 *This class is an interface for allowing operations with ngrams
 * 
 * @author David Przybilla
 */
public class Ngram {
    
    //this scape character is used when generating the ngrams
    private static String scapeCharacter="^";
    
    
    
    /*
     * Returns the number of Ngrams which are generated for a String S if ngrams are of Size ngramSize.
     * @param S string (with no preprocessing(no added scapeCharacters), from which the number of ngrams will be calculated
     * @parm ngramSize number of ngram size
     */
    public static int getNumberOfNgrams(String S,int ngramSize){
        String temp=S.replace(Ngram.scapeCharacter, "");
        int count=0;
        count=temp.length()+ngramSize-1;
        return count;
        
    }
    
      /*
     * Returns the number of Ngrams which are generated for a String S of size sizeOfS if ngrams are of Size ngramSize.
     * @param sizeOfS size of string (with no preprocessing(no added scapeCharacters), from which the number of ngrams will be calculated
     * @parm ngramSize number of ngram size
     */
      public static int getNumberOfNgrams(int sizeOfS,int ngramSize){
        int count=0;
        count=sizeOfS+ngramSize-1;
        return count;
       
    }
    
      /*
     * Adds the scapes characters to a string for allowing the right calculatiojn of ngrams
     * i.e: preprocessTerm(dog,3) -> ^^dog^^ 
     * 
     * @param S string which will be preprocessed
     * @param size ngram size
     * @return S surronded with scape characters based on the ngram size
     * 
     * 
     */
    public static String preprocessTerm(String S,int size){
    
        for(int i=0;i<size-1;i++){
            S=(Ngram.scapeCharacter+S+Ngram.scapeCharacter);
        }
        return S;
    }
    
    
    
       /*
     * Given a string S and a size Of ngrams , it returns the list of ngrams for string S
     * 
     * @param S string which want to get a list of ngrams from
     * @param size ngram size
     * @return list of ngrams for string S
     * 
     * 
     */
    public  static ArrayList<String> splitIntoNGrams(String S,int size){
        
        //this variables represents the interval of the window that generates the ngrams
        int windowRangeLowerBound=0;
        int windowRangeUpperBound=size;
        

        
        //outputlist of ngrams        
        ArrayList<String> ngramList=new ArrayList(S.length()+size-1);
       HashMap<String,Integer> seenTerms=new HashMap<String,Integer>();
         
       
        
        while(windowRangeUpperBound<=S.length()){
            //calculate the ngram
            String ngram=S.substring(windowRangeLowerBound, windowRangeUpperBound);
            
            if(seenTerms.containsKey(ngram)){
                String currentNgram=ngram;
                int seentimes=seenTerms.get(ngram);
                ngram=ngram+"-"+seentimes;
                seenTerms.put(currentNgram,seentimes+1);
            }
            else{
                 seenTerms.put(ngram,1);
                  ngram=ngram+"-"+1;
            
            }
            
            //add the ngram to the list
            ngramList.add(ngram);
       
            //update the window for the next iteration
            windowRangeLowerBound+=1;
            windowRangeUpperBound+=1;
        }
        
        return ngramList;
        
    
    }
    
}
