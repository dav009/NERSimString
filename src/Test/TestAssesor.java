/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author David Przybilla
 */
public class TestAssesor {
    
    //average time of running
   public double averageTime;
    
    //hieghst time
   public double highestTime;
    
    //defines number of times to run the test
    public int numberOfTimesToRun;
    
    private double sumOfTime;
    
    //test class to run
    Test test;
    
    public TestAssesor(Test t,int numberOfTimesToRun){
        this.test=t;
        this.numberOfTimesToRun=numberOfTimesToRun;
    }
    
    public double run(){
            
            for(int i=0;i<this.numberOfTimesToRun;i++){
                long start = System.nanoTime();
                test.run();
                double elapsedTimeInSec = (System.nanoTime() - start) ;  
                sumOfTime+=elapsedTimeInSec;
            }
            
            this.averageTime=sumOfTime/this.numberOfTimesToRun;
            
            return this.averageTime;
    }
    
}
