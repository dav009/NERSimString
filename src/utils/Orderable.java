/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 *This class is a hack for allowing lists of Ids of Entities 
 * to be sorted accordding to the number of elements that the list contains.
 * 
 * Theoretically it can be used to sort of kind of objects.
 * 
 * @author David Przybilla
 */
public class Orderable<T>  implements Comparable {
    
    //key for identifying an object
    private int key;
    //value which will be used for sorting
    private int value; 
    //the object itself
    private T object;
    
    
    public Orderable(T t,int key,int value){
        this.object=t;
        this.key=key;
        this.value=value;
    }
    
    /*
     returns the key of the object
     */
    public int getKey(){
      return key;
    }
    
    /*
     returns the value f the object
     */
    public int getValue(){
        return value;
    }
    
    /*
     returns the object associated with the othr fields
     */
    public T getObject(){
        return object;
    }

    /*
     ordering is done by comparing t.value and this.value
     */
    @Override
    public int compareTo(Object t) {
        Orderable or=(Orderable) (t);
        return this.value-or.value;
    }
    
    
    
}
