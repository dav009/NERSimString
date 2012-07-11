/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simstring;

import Measures.Measure;
import java.util.*;
import Dictionary.DictionaryGenerator;
import utils.Ngram;
import utils.Orderable;

/**
 *
 * This class is the main algorithm related to simString
 * @author David Przybilla
 */
public class SimString {
    
    
    public static String defaultDictionaryGenerator="NaiveDictionaryGenerator";
    
    //returns a dictionary .db file out of the file given in path
    public static void generateDictionary(String path){
        
        //read file
        //read line
            //convert each line into ngrams
            //add each ngram into the inverted list
    
    }
    
    
    /*
     * this method performs the search in a dictionary of a string S
     * @param d is a dictionary in which simString algorithm will make lookup
     * @param s is the string that will be looked for in d
     * @return matches [not yet implemented only prints them out]
     */
    
    public static void search(DictionaryGenerator d,Measure m,String s){
        
        //preprocessing S(adding extra characters as described in the paper)
        s=Ngram.preprocessTerm(s, d.getNgramSize());
        
        //looking for the range in which strings have to be looked up
        int max=m.max(s,d.getNgramSize());
        int min=m.min(s,d.getNgramSize());
        
        //just debugging
        System.err.println("------------- ");
        System.err.println("searching: "+s);
        System.err.println("range: ["+min+","+max+"]");
        
        
        //set of matches
        HashSet<Integer> solutions=new HashSet<Integer>();
        
        //iterate through all the posible sizes
        for(int l=min;l<=max;l++){
            //min overlap called t in the paper
            int min_overlap=m.t(s,d.getNgramSize(), l);
            System.err.println("min_overlap:"+min_overlap);
            
            //look for solutions for strings with size l
            ArrayList<Integer> matches=overlap_nonnaive(s,min_overlap,d,l);
            
            //add the found solutions to the set of solutions
            for(int i=0;i<matches.size();i++){
                solutions.add(matches.get(i));
            }
        }
        
        //decode solutions, since they are used with id's a translation to strings have to be done :)
        System.err.println("Result:");
        Iterator sol=solutions.iterator();
        while(sol.hasNext()){
            
            int id=(Integer)sol.next();
            System.err.println("\t "+ d.getTerm(id));
            
           
        }
         
    
    }
    
    
    /*
     * Implementation of the efficient algorithm given in the paper
     * Searches for matches of size l for string S, whose minmum overlap of ngrams is min_overlap, in the dictionary d
     * @param s string we are looking for
     * @param min_overlap min number of matching ngrams between s and Y, for Y to be a match for S
     * @param d dictionary
     * @param l size of Y, given that Y is a match for S
     * @return the list of Ids which are matches to S of size L and whose min number of common ngrams with S is min_overlap
     */
    private static ArrayList<Integer> overlap_nonnaive(String s, int min_overlap, DictionaryGenerator d, int l){
     //list of matches to return at the end
     ArrayList<Integer> listOfMatches=new ArrayList<Integer>(); 
     
     //list of ngrams of the string S
        ArrayList<String> ngramList=Ngram.splitIntoNGrams(s, d.getNgramSize());
        
        //count of how many times a string has matched, in order to make sure the overlap is higher than min_overlap
       HashMap<Integer,Integer> m=new HashMap<Integer,Integer>();
       
       //order listOfNgrams increnmentally to the least common ngram to the most common
       PriorityQueue queueEntitiesPerNgram=new PriorityQueue();
       
       
       for(int i=0;i<ngramList.size();i++){
           ArrayList<Integer> tempListOfTermsForNgramI=d.searchTerm(l, ngramList.get(i));
           Orderable<ArrayList<Integer>> orderable_temp=new Orderable<ArrayList<Integer>>(tempListOfTermsForNgramI,i,tempListOfTermsForNgramI.size());
           queueEntitiesPerNgram.add(orderable_temp); 
       }
       
      
       List<Orderable<ArrayList<Integer>>> queueIntoList = new LinkedList<Orderable<ArrayList<Integer>>>(queueEntitiesPerNgram);
       ArrayList<Orderable<ArrayList<Integer>>> listOfEntitiesPerNgram=new ArrayList<Orderable<ArrayList<Integer>>>(queueIntoList);
            
      int numberOfNgramsForS=Ngram.getNumberOfNgrams(s, d.getNgramSize());
       
       for(int k=0;k<=numberOfNgramsForS-min_overlap;k++){
           for(int z=0;z<listOfEntitiesPerNgram.get(k).getObject().size();z++){
                int currentMatch=listOfEntitiesPerNgram.get(k).getObject().get(z);
                int currentCount=0;
                if(m.containsKey(currentMatch)){
                    currentCount=m.get(currentMatch);
                }
                else{
                    m.put(currentMatch, 0);
                }
                int newValue=currentCount+1;
                m.put(currentMatch,newValue);
                
           }
       
       }
       
      
       
            List<Integer> listOfM_ = new LinkedList<Integer>(m.keySet());
            ArrayList<Integer> listOfM=new ArrayList<Integer>(listOfM_);
            
       for(int k=numberOfNgramsForS-min_overlap+1;k<listOfEntitiesPerNgram.size();k++){
           for(int z=0;z<listOfM.size();z++){
               int currentZ=listOfM.get(z);
               
               //if z is found in the list K
               if(find(currentZ,listOfEntitiesPerNgram.get(k).getObject())){
                   m.put(currentZ,m.get(currentZ)+1);
               }
               if(min_overlap<=m.get(currentZ)){
                   listOfMatches.add(currentZ);
               }
               
           }
       
       }
    
       return listOfMatches;
     
    }
    
    
     /*
     * THIS IS NOT LONGER USED was just implemented in the first VERSION
     * Implementation of the inefficient algorithm given in the paper
     * Searches for matches of size l for string S, whose minmum overlap of ngrams is min_overlap, in the dictionary d
     * @param s string we are looking for
     * @param min_overlap min number of matching ngrams between s and Y, for Y to be a match for S
     * @param d dictionary
     * @param l size of Y, given that Y is a match for S
     * @return the list of Ids which are matches to S of size L and whose min number of common ngrams with S is min_overlap
     */
    private static ArrayList<Integer> overlap(String s, int min_overlap, DictionaryGenerator d, int l) {
        //list of matches to return at the end
       ArrayList<Integer> listOfMatches=new ArrayList<Integer>(); 
       
       //list of ngrams of the string S
        ArrayList<String> ngramList=Ngram.splitIntoNGrams(s, d.getNgramSize());
       
       //count of how many times a string has matched, in order to make sure the overlap is higher than min_overlap
       HashMap<Integer,Integer> m=new HashMap<Integer,Integer>();
       
       
       for(int i=0;i<ngramList.size();i++){
           
           
           String ngram=ngramList.get(i);
           ArrayList<Integer> matches_= d.searchTerm(l, ngram);
           System.err.println("looking for matches of ngram: "+ngramList.get(i)+ " in strings of size: "+l+" --Matches:"+matches_.size());
           
           HashSet<Integer> matches=new HashSet<Integer>();
           for(int j=0;j<matches_.size();j++){
             matches.add(matches_.get(j));
               
           }
           
           Iterator it=matches.iterator();
           
            while(it.hasNext()) {
                
             
                Integer currentMatch=(Integer)it.next();
                  
                int currentCount=0;
                if(m.containsKey(currentMatch)){
                    currentCount=m.get(currentMatch);
                }
                else{
                    m.put(currentMatch, 0);
                }
                int newValue=currentCount+1;
                m.put(currentMatch,newValue);
                 
                
                if(min_overlap<=newValue){
                    System.err.println("match:"+currentMatch+"--"+newValue);
                    listOfMatches.add(currentMatch);
                }
           }
           
       }
       
       
       return listOfMatches;
       
    }

    
      /*
     * Auxiliary Function for looking overlaps.
     * listOfIds has to be ordered since a binarySeach is done to look for currentZ in listOfIds
     * @param currentZ a current Id of an entity
     * @param listOfIds list of entities Ids (ordered listOfIds)
     * @return whether currentZ is in the listOfIds
     */
    private static boolean find(int currentZ, ArrayList<Integer> listOfIds) {
       boolean found=false;
      int result=Arrays.binarySearch(listOfIds.toArray(), (Integer) currentZ);
       if(result>0){
           found=true;
       }
       return found;
    }
    
}
