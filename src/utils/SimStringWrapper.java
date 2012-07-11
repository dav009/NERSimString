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
 * TOTAL_HOURS_WASTED = 3
 */
public class SimStringWrapper
{
     public static String pathToExecutable="";
     public static WorkingEnvironment environment = WorkingEnvironment.WINDOWS;

     public static void constructDictionary(String sourceDataPath, String dbOutputPath)
     {
         String command = pathToExecutable + " -b -d " + dbOutputPath + " < " + sourceDataPath;
         callSimString(command);
     }
    
    public static  ArrayList<String> search(String query, String pathToDictionary,double threshold, SimilarityMeasure similarityMeasure) throws IOException
    {
        ArrayList<String> results=new ArrayList<String>();
        String command="echo " + query + " | " + pathToExecutable + " -d " + pathToDictionary+" -t "+threshold+" -s "+similarityMeasure.toString().toLowerCase();

        for (String item : callSimString(command))
        {
            System.out.println(item);
        }
        
        return results;
    }

       public static ArrayList<String> callSimString(String command){

           ArrayList<String> result = new ArrayList<String>();
           if (environment == WorkingEnvironment.WINDOWS)
           {
               command = "cmd /c " + command;
           }

           try
           {
               Runtime rt = Runtime.getRuntime();
               Process p = rt.exec(command);
               p.waitFor();
               BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
               String line="";
               while(((line = reader.readLine()) != null))
               {
                   result.add(line);
               }

               p.destroy() ;

           }catch(Exception exc){System.out.println(exc.getMessage());}

           return result;
       }
}

