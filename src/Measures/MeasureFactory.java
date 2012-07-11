/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Measures;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is an interface for constructing Measures
 * @author attickid
 */
public class MeasureFactory {
 
    //list of Available Similarities
    public static String COSINE_SIMILARITY=Cosine.class.getName();
    public static String JACCARD_SIMILARIY=Jaccard.class.getName();
    public static String DICE_SIMILARITY=Dice.class.getName();
    
    
    /*
     * @param similarity constant telling the class which is the desired measure to construct
     * @param threshold given number for the threshold
     */
    public static Measure create(String similarity,double threshold) {
        
         Measure m = null;
         
        try {
          //takes the name of the class and create an instance of the class with its name
           Class<Object> measureClass=(Class<Object>) Class.forName(similarity);
           //gets one of the constructors for the class
           //creates and object with the constructor
           //cast it to measure
           m=(Measure) measureClass.getDeclaredConstructors()[0].newInstance(threshold);
           
        } catch (Exception ex) {
            Logger.getLogger(MeasureFactory.class.getName()).log(Level.SEVERE, "No valid similarity Class was selected", ex);
        }
    
        return m;
    }
    
}
