/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Measures;

/**
 * Abstract class for handling different Measures effienctly
 * @author attickid
 */
public abstract class Measure {
    
    protected double threshold;
    
    /*
     * @param threshold the threshold used in the measure
     */
    public Measure(double threshold){
        this.threshold=threshold;
    }
    
     /* @param ngramSize the ngramSize
     * @param x the string we want to find matches for
     * @return the min lenght of a string 'Y' that might match string 'X' given the threshold
     */
    public abstract int min(String x,int ngramSize);
    
    /* @param ngramSize the ngramSize
     * @param x the string we want to find matches for
     * @return the max lenght of a string 'Y' that might match string 'X' given the threshold
     */
    public abstract int max(String x,int ngramSize);
    
      /* @param ngramSize the ngramSize
     * @param x the string we want to find matches for
     * @param assumming that Sy is a match candidate for X, y is the lenght of Y
     * @return returns the min number of matches of ngram for a string of lenght y to be  match for X
     */
    public abstract int t(String x,int ngramSize,int y);
    
}
