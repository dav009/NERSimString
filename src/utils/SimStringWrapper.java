package utils;

import java.io.*;
import java.util.ArrayList;

/**
 * User: Boris Kozlov
 * Date: 16/06/12
 * Time: 17:12
 */

/*
 * BUG: does not run in Windows when the path to the executable contains spaces.
 * Please don't try to solve this issue.
 * If you have not listened and tried to solve it please increment the value below.
 * TOTAL_HOURS_WASTED = 6
 */
public class SimStringWrapper
{
     public static String pathToExecutable="/home/attickid/NetBeansProjects/simstring/src/originalSimString/frontend/";
     
     
   

     public static  ArrayList<String> constructDictionary(String sourceDataPath, String dbOutputPath,boolean unicode)
     {
         String aux_command="-b";
         if(unicode){
             aux_command+="u";
         }
         
         String[] command = {pathToExecutable+"createDictionaryBash", aux_command, dbOutputPath, sourceDataPath,pathToExecutable+"simstring"};
         System.out.println(pathToExecutable+"createDictionaryBash"+" "+ aux_command+" "+ dbOutputPath+" "+ sourceDataPath+" "+pathToExecutable+"simstring");
         return callSimString(command);
     }
    
    public static  ArrayList<String> search(String query, String pathToDictionary,double threshold, SimilarityMeasure similarityMeasure) throws IOException
    {
        ArrayList<String> results=new ArrayList<String>();
        
      
        String[] command={pathToExecutable+"makeSearchBash", pathToDictionary,threshold+"",similarityMeasure.toString().toLowerCase(),query,pathToExecutable+"simstring"};

        System.out.println(command[0]);
        for (String item : callSimString(command))
        {
        	results.add(item);
            System.out.println(item);
        }
        //remove the last results where it is shown how many results were found
        results.remove(results.size()-1);
        
        return results;
    }

       public static ArrayList<String> callSimString(String[] command){

           ArrayList<String> result = new ArrayList<String>();
           

           try
           {
              
              // System.out.println("calling: "+command);
               
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                
                Process p= processBuilder.start();
               
              
               
               p.waitFor();
               
               
               BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
               String line="";
               while(((line = reader.readLine()) != null))
               {
                   result.add(line);
                   System.out.println(line);
               }

               p.destroy() ;

           }catch(Exception exc){System.out.println(exc.getMessage());}

           return result;
       }
}

