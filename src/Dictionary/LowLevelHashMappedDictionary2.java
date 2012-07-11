/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;


import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.helper.FastIterator;
import jdbm.htree.HTree;

/**
 *
 * @author attickid
 */
public class LowLevelHashMappedDictionary2 implements LowLevelDictionaryImplementation, java.io.Serializable{
    
    
    RecordManager mapOfTerms_db; 
    RecordManager mapOfIds_db;
    RecordManager mapOfSizes_db;
    
    private static int max_transactions=3000;
    
       HTree mapOfTerms;
       HTree mapOfIds;
       HTree mapOfSizes;
    
        //size of ngrams
        int ngramSize;

    //current maxID of a term
    private int id=0;
    
    private int transactionCounter=0;
    private String  pathToDictionary;
    
    
    //loads a dictionary given ngramsize and pathToDictionary
    private LowLevelHashMappedDictionary2(String pathToDictionary,int ngramsize){
        try {
            this.pathToDictionary= pathToDictionary;
            this.ngramSize=ngramsize;
            //loads everything
              Properties props = new Properties();
              
              
              
                 mapOfTerms_db=  RecordManagerFactory.createRecordManager( pathToDictionary+"_terms",props);
                 mapOfIds_db=  RecordManagerFactory.createRecordManager( pathToDictionary+"_ids",props);
                 mapOfSizes_db=  RecordManagerFactory.createRecordManager( pathToDictionary+"_sizes",props);
                 
                 long recid = mapOfTerms_db.getNamedObject("mapOfTerms" );
                 mapOfTerms =  HTree.load(mapOfTerms_db, recid);
                 
                 recid = mapOfIds_db.getNamedObject("mapOfIds" );
                 mapOfIds =  HTree.load(mapOfIds_db, recid);
                 
                 
                 recid = mapOfSizes_db.getNamedObject("mapOfSizes" );
                 mapOfSizes =  HTree.load(mapOfSizes_db, recid);
           
                
        } catch (IOException ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public LowLevelHashMappedDictionary2(int ngramSize,String pathToDictionary){
        try {
            Properties props = new Properties();
            this.pathToDictionary= pathToDictionary;
             
             
             mapOfTerms_db=  RecordManagerFactory.createRecordManager( pathToDictionary+"_terms",props);
             mapOfIds_db=  RecordManagerFactory.createRecordManager( pathToDictionary+"_ids",props);
             mapOfSizes_db=  RecordManagerFactory.createRecordManager( pathToDictionary+"_sizes",props);
             
             
               
             
             mapOfTerms =  HTree.createInstance( mapOfTerms_db);
             mapOfTerms_db.setNamedObject( "mapOfTerms", mapOfTerms.getRecid() );
             
             mapOfIds =  HTree.createInstance(mapOfIds_db);
             mapOfIds_db.setNamedObject( "mapOfIds", mapOfIds.getRecid() );
             
             mapOfSizes =  HTree.createInstance( mapOfSizes_db);
             mapOfSizes_db.setNamedObject( "mapOfSizes", mapOfSizes.getRecid() );
             
             this.ngramSize=ngramSize;
        } catch (IOException ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       

   }

        
   /*
    * saves in to the files the current table of ids, sizes and terms
    */
   public void commit(){
        try {
            this.mapOfIds_db.commit();
            this.mapOfSizes_db.commit();
            this.mapOfTerms_db.commit();
            
            //serialize the object
         //   System.out.println("path to dict: "+this.pathToDictionary);
          //  FileOutputStream fileOut = new FileOutputStream(this.pathToDictionary);
           // ObjectOutputStream out =new ObjectOutputStream(fileOut);
            //out.writeObject(this);
            //out.close();
            //fileOut.close();
     
        } catch (Exception ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    /* Given a path it loads the dictionary found in that path and retuns a lowLevel representation
    * @param id term id
    */
   public static LowLevelHashMappedDictionary2 load(String dictionaryPath,int ngramSize){
       return new LowLevelHashMappedDictionary2(dictionaryPath,ngramSize);
   }
   
    /* given an id of a term returns its String representation 
    * @param id term id
    */
    public String getTerm(int id){
        try {
            String r=(String) mapOfIds.get(id);
            if(r!=null)
                    return r;
           
                
        } catch (IOException ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    /* given a term returns its id
    * @param term the term from which the id will be returned
    */
    public int getId(String term){
        
        try {
            
            Integer id_;
            id_ = (Integer)mapOfTerms.get(term);
             if( id_!=null){
            return id_;
    } else{
           return id_;
        }
            
        } catch (IOException ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
       
    
       

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
     
            try {
                Integer id_=(Integer)mapOfTerms.get(word);
            if(id_==null){
                mapOfTerms.put(word,id);
                mapOfIds.put(id,word);
                id++;
            
                transactionCounter++;
             }
            } catch (IOException ex) {
                 System.err.print(ex.getMessage());
            }
         
      
     if(transactionCounter>max_transactions){
            try {
                mapOfTerms_db.commit();
                mapOfIds_db.commit();
                transactionCounter=0;
            } catch (IOException ex) {
                Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
        
        
        
    }

    /* states that the list Of entities which contains the ngram-size is in p.
     * @param ngram_size the key for which the list p can be searched "ngram-sizeOfStringsInP"
     * @param p list of ordered ids of the entities which contain ngram "ngram" and are of size "size"
    */
    public void putEntity(String ngram_size,PriorityQueue<Integer> p){
        try {
            this.mapOfSizes.put(ngram_size,p);
            transactionCounter++;
             if(transactionCounter>max_transactions){
             mapOfSizes_db.commit();
             transactionCounter=0;
         }
        } catch (IOException ex) {
             System.err.print(ex.getMessage());
        }
        
    }

    /*
    * returns the list of entities which have an ngram "ngram" and the entities are of size "size"
    */
    public PriorityQueue<Integer> getEntities(String ngram_size){
        PriorityQueue<Integer> p=null;
        try {
            p=(PriorityQueue<Integer>) this.mapOfSizes.get(ngram_size);
            if(p!=null)
                return p;
            
            
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
        
               
                return new PriorityQueue<Integer>();
    }

    /*
    * returns whether a key with a shape "ngram-sizeOfY" is in the dict
    */
    public boolean containsNgramSize(String ngram_size){
            boolean r=false;
        Object result=null;
        try {
            result = this.mapOfSizes.get(ngram_size);
        } catch (IOException ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result!=null){
            r=true;
        }
        
        return r;
    }

    /*
     * returns whether the string Word is in the list of terms (has an id)
     */
    public boolean containsTerm(String word)
    {
        boolean r=false;
        Object result=null;
        try {
            result = mapOfTerms.get(word);
        } catch (IOException ex) {
            Logger.getLogger(LowLevelHashMappedDictionary2.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result!=null){
            r=true;
        }
        
        return r;
    }


}
