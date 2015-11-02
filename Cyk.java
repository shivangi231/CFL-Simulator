package final_internship;


import java.util.*;

public class Cyk
{
     public String table[][];
     Rule R;
     
     public Cyk()
    {
        R = new Rule();
    } 
     
     /**
      * checkGrammar calls for the input to be read, initializes the table
      * and fills the table dynamically.  The final answer is placed in
      * V[0][n] box of the Matrix V.  The final table is then printed.
      */
    public void checkGrammar()
    {
        R.readInput();
        table = new String[R.input.size()][R.input.size()];
        
        //initialize table
        for(int i = 0; i < R.input.size(); i++)
        {
            for(int j = 0; j < R.input.size(); j++)
            {               
                table[i][j] = "";
            }
        }

        //insert the first line
        for(int i = 0; i < R.input.size(); i++)
        {
            for(int j = 0; j < R.rules.size(); j++)
            {
                ArrayList value = new ArrayList();
                value = (ArrayList)R.rules.get(j);
                if(R.input.get(i).equals(value.get(1)))
                {
                    table[i][0] = (String)table[i][0] + value.get(0);
                }
            }
        }

        //compute Vij
        for(int j = 1; j < R.input.size(); j++)
        {
            for(int i = 0; i < R.input.size() - j; i++)
            {
                for(int k = 0; k < j; k++)
                {                    
                    for(int m = 0; m < R.rules.size(); m++)
                    {
                        ArrayList value = new ArrayList();
                        value = (ArrayList)R.rules.get(m);
                                                
                        //only come in here if the rule goes to nonterminals
                        if(value.size() > 2)
                        {
                            //check if either of the boxes is null - they shouldn't be
                            if((!table[i+k+1][j-k-1].equals("")) && (!table[i][k].equals("")))
                            {
                                //cross 1 nonterminal with 1 nonterminal
                                if(((table[i][k].equals((String)value.get(1))) 
                                 && (table[i+k+1][j-k-1].equals((String)value.get(2)))))
                                {
                                    checkDuplicates(i, j, value);                                    
                                }
                                                  
                                //cross 1 nonterminal with 2 nonterminals
                                else if((table[i][k].length()==1) && (table[i+k+1][j-k-1].length()==2))
                                {
                                    if(((table[i][k].equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    ||  (table[i][k].equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                     {
                                          checkDuplicates(i, j, value);
                                     }
                                }
                                
                                //cross 1 nonterminal with 3 nonterminals
                                else if((table[i][k].length()==1) && (table[i+k+1][j-k-1].length()==3))
                                {
                                    if(((table[i][k].equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    || ((table[i][k].equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(2, 3).equals((String)value.get(2)))))
                                     {
                                        checkDuplicates(i, j, value);
                                     }
                                }
                                
                                //cross 2 nonterminals with 1 nonterminals
                                else if((table[i][k].length()==2) && (table[i+k+1][j-k-1].length()==1))
                                {
                                    if(((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))))
                                     {
                                        checkDuplicates(i, j, value);
                                     }
                                }
                                
                                //cross 2 nonterminals with 2 nonterminals
                                else if((table[i][k].length()==2) && (table[i+k+1][j-k-1].length()==2))
                                {
                                    if(((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    || ((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2)))))
                                     {
                                            checkDuplicates(i, j, value);
                                     }
                                }
                                 
                                //cross 2 nonterminals with 3 nonterminals
                                else if((table[i][k].length()==2) && (table[i+k+1][j-k-1].length()==3))
                                {
                                    if(((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    || ((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(0, 1).equals((String)value.get(1)))
                                    && (table[i+k+1][j-k-1].substring(2, 3).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(2, 3).equals((String)value.get(2)))))
                                     {
                                            checkDuplicates(i, j, value);
                                     }
                                }
                                
                                 //cross 3 nonterminals with 1 nonterminal
                                 else if((table[i][k].length()==3) && (table[i+k+1][j-k-1].length()==1))
                                 {
                                    if(((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].equals((String)value.get(2)))) 
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].equals((String)value.get(2))))
                                    || ((table[i][k].substring(2, 3).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].equals((String)value.get(2)))))
                                     {
                                         checkDuplicates(i, j, value);
                                     }
                                }
                                
                                //cross 3 nonterminals with 2 nonterminals
                                else if((table[i][k].length()==3) && (table[i+k+1][j-k-1].length()==2))
                                {
                                    if(((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    || ((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1)))
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(2, 3).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2))))
                                    || ((table[i][k].substring(2, 3).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2)))))
                                     {
                                            checkDuplicates(i, j, value);
                                     }
                                } 
                                
                                //cross 3 nonterminals with 3 nonterminals
                                else if((table[i][k].length()==3) && (table[i+k+1][j-k-1].length()==3))
                                {
                                    if(((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2)))) 
                                    || ((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(0, 1).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(2, 3).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1)))
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(1, 2).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(2, 3).equals((String)value.get(2))))
                                    || ((table[i][k].substring(2, 3).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(0, 1).equals((String)value.get(2))))
                                    || ((table[i][k].substring(2, 3).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(1, 2).equals((String)value.get(2))))
                                    || ((table[i][k].substring(2, 3).equals((String)value.get(1))) 
                                    && (table[i+k+1][j-k-1].substring(2, 3).equals((String)value.get(2)))))
                                     {
                                            checkDuplicates(i, j, value);
                                     }
                                }
                                
                                /** limitations of the program
                                 *  using a language like java, to increase the
                                 *  program to handle 4 nonterminals in a box
                                 *  would require an additional 7 if statements,
                                 *  an additional 9 more statements for lengths
                                 *  of 5, etc.
                                 *  This would added at this point.
                                 */
                                else if((table[i][k].length() > 3) || (table[i+k+1][j-k-1].length() > 3))
                                {
                                    System.out.println
                                    ("Sorry, the program has not been developed to this point...exiting");
                                    System.exit(0);
                                }
                            }//end if
                        }
                    }
                }//end k loop
            }//end i loop           
        }//end j loop
       printV(); 
        
    }//end checkGrammar
    
    /**
     * checkDuplicates scans through the box we are looking at and sees
     * if the nonterminal we are about to add is already in the box.
     * It then adds the value if not already present.  If I had used a 
     * TreeSet instead of a standard 2D array to manage this, I would 
     * have no need to do this as it does not allow duplicates.
     * However, since the output is in the form of a 2D array, it seemed
     * more natural to use a 2D array and write this small procedure.
     * 
     * @param first is an integer that holds the i value of the table
     * @param second is an integer that holds the j value of the table
     * @param value1 is an ArrayList of the productions that are being checked
     */
    private void checkDuplicates(int first, int second, ArrayList value1)
    {      
        if(table[first][second].length()>0)
        {
            boolean present = false;
            
            //scan what's in the table already
            for(int z = 0; z < table[first][second].length(); z++)
            {                  
               if(table[first][second].substring(z, z+1).equals((String)value1.get(0)))
               {
                   present = true;
               }
           }
            if(!present)
            {
                /** there is no duplication, therefore put everything back in the
                 *  table as well as the new nonterminal
                 */
                   table[first][second] = table[first][second] + (String)value1.get(0);
            }
        }
        else
        {
            table[first][second] = (String)value1.get(0);
        }            
    }//end checkDuplicates
    
    /** 
     * printV prints the V Matrix and the conclusion
     * of whether or not the string is a member of L(G)
     */
    private void printV()
    {
        System.out.println("=====  V Matrix  ====="); 
         for(int k = 0; k < R.input.size(); k++)
        {           
            for(int i = 0; i < R.input.size(); i++)
            { 
                if((table[i][k].length()==0) && (i + k < R.input.size()))
                {
                   table[i][k] = "nil"; 
                }
                System.out.print(table[k][i] + " ");               
            }
            System.out.println();
        }
        System.out.println("======================");

        for(int z = 0; z < table[0][R.input.size()-1].length(); z++)
        {
            if(table[0][R.input.size()-1].substring(z, z+1).equals("S"))
            {
               System.out.println("This string is in L(G)"); 
               break;
            }
            else
            {
                System.out.println("This string is not a member of L(G)"); 
                break;
            }
        }
    }//end printV
}//end class
