package final_internship;

import java.lang.*;

import java.io.*;
import java.util.*;

public class Rule 
{
    public ArrayList temp = new ArrayList(); //for working with file
    public ArrayList rules;                  //for ArrayList of ArrayLists
    public ArrayList input;                  //holding the input string
    public int location;                     //find the location of the colon
    
    public Rule()
    { 
        rules = new ArrayList();
        input = new ArrayList();
    }
    
    /**
     * Read the input from a file and put it in the various ArrayLists 
     * for processing by the CYK algorithm 
     */
    public void readInput() 
    { 
        try
        { 
          File inputFile = new File("C:/Users/Rohan/Desktop/grammar1.txt");
          FileReader in = new FileReader(inputFile);
        
          int c;
          int counter = 0;
          
        //read to end of file
        while ((c = in.read()) != -1)
        {   
            //this deletes the word Productions:
            if(counter > 12)
            {
                /** deletes the cr, -, >
                  * leaves newline for later
                  */
                if((c != 13) && (c != 45) && (c != 62))
                {                   
                   temp.add(Character.toString((char)c)); 
                   
                  //used for printing if not using ArrayLists
                  //System.out.println((char)c);
                }
            }
            counter++;              
        }
        
        in.close();
        
        //find the colon after "string"
        for(int i = 0; i < temp.size(); i++)
        {   
            //Integer d = new Integer(58);
            if(temp.get(i).equals(":"))
            {
                location = i;
            }
        }
        
        //push the input string into it's own ArrayList
        for(int i = location +2; i < temp.size(); i++)
        {
            String s = (String)temp.get(i); 
            input.add(s);
        }
        
        //remove the newline from this list
        int nl = input.size() - 1;
        input.remove(nl);
        
        //remove the word string: and string input
        for(int i = temp.size() - 1; i > location - 7; i--)
        {
            temp.remove(i);
        }
                 
        //determine the location of carriage returns and pipes
        String e = new String("\n");
        String f = new String("|");
        counter = 0;
        
        //remove the first newline before counting
        temp.remove(0);
        
        //count the number of production rules
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).equals(e))
            {
                counter++;
            }
        }

       /**an ArrayList of ArrayLists
        * program is only able to handle a maximum of 3 productions/LHS
        * it could be expanded easily within the while loop
        */
       int j = 0;
       for(int i = 0; i < counter; i++)
       {    
           ArrayList list1 = new ArrayList();
           ArrayList list2 = new ArrayList();
           ArrayList list3 = new ArrayList();
           
           /**as it prints the rule, when it encounters
            * a pipe, it puts that into another rule
            */
           while(!temp.get(j).equals(e))
           {   
               if(!temp.get(j).equals(f))
               {
                   list1.add(temp.get(j));
                   j++;
               }
               else
               {
                   list2.add(list1.get(0));
                   list2.add(temp.get(j + 1));
                   j+=2;
                   
                   while(!temp.get(j).equals(e))
                   {
                       if(!temp.get(j).equals(f))
                       {
                            list2.add(temp.get(j));
                            j++;
                        }
                        else
                        {
                            list3.add(list1.get(0));
                            list3.add(temp.get(j + 1));
                            j+=2;
                            
                            //for additional rules add while loop here
                        }
                   }//end second while loop
               }
           }//end first while loop

           rules.add(list1);
           if(!list2.isEmpty())
           {
               rules.add(list2);
           }
           if(!list3.isEmpty())
           {
               rules.add(list3);
           }

           //move past the newline but not past array end
           if(temp.get(j).equals(e) && (j != temp.size()-1))
           {
                j++;
           }
       }//end for loop
       
      printR(rules);
      
      //print input string
        System.out.println("======  Input  ======");       
        for(int i = 0; i < input.size(); i++)
        {
            System.out.print(input.get(i) + " ");
        }
        System.out.println();
        System.out.println("=====================");
        System.out.println();
    
     }//end try
               
      catch(FileNotFoundException e)
      {
        System.out.println("File not found" + e);   
      }
      catch(IOException e)
      {
        System.out.println("Input output error" + e);   
      }
   }//end readInput
  
    /**
     * Prints the production rules
     * @param R 2D ArrayList that holds the productions
     */
      private void printR(ArrayList R)
        {
            System.out.println("=====  Grammar  =====");
            for(int i = 0; i < R.size(); i++)
                {
                    ArrayList value = new ArrayList();
                    value = (ArrayList)R.get(i);
                    for(int j =0; j < value.size(); j++)
                    {
                        System.out.print( value.get(j) + "  ");       
                    }    
                    System.out.println();
                }
            System.out.println("=====================");
            System.out.println();
        }//end printR  
}//end class