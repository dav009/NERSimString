/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Measures;

import utils.Ngram;

/**
 * This class represents the cosine measure
 * @author attickid
 */
public class Cosine extends Measure {
    
  
    /*
     * @param threshold the threshold used in the measure
     */
    public Cosine(double threshold){
        //just call the superclass constructor
        super(threshold);
    }

    /* @param ngramSize the ngramSize
     * @param x the string we want to find matches for
     * @return the min lenght of a string 'Y' that might match string 'X' given the threshold
     */
    @Override
    public int min(String x,int ngramSize) {
      
      int numberOfNgrams=Ngram.getNumberOfNgrams(x, ngramSize);
      double ceil= Math.floor(this.threshold*this.threshold*numberOfNgrams);
      return (int) ceil;
    }

    /* @param ngramSize the ngramSize
     * @param x the string we want to find matches for
     * @return the max lenght of a string 'Y' that might match string 'X' given the threshold
     */
    @Override
    public int max(String x,int ngramSize) {
      int numberOfNgrams=Ngram.getNumberOfNgrams(x, ngramSize);
      double ceil= Math.ceil(numberOfNgrams/(this.threshold*this.threshold));
      return (int) ceil;
    }

    /* @param ngramSize the ngramSize
     * @param x the string we want to find matches for
     * @param assumming that Sy is a match candidate for X, y is the lenght of Y
     * @return returns the min number of matches of ngram for a string of lenght y to be  match for X
     */
    @Override
    public   int t(String x,int ngramSize,int y) {
      int numberOfNgrams=Ngram.getNumberOfNgrams(x, ngramSize);
      int numberOfNgramsy=Ngram.getNumberOfNgrams(y, ngramSize);
      double ceil= Math.sqrt(numberOfNgrams*numberOfNgramsy)*this.threshold;
      return (int) ceil;
    }
    
    
}
