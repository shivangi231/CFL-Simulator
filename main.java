package final_internship;

public class main
{
    public static void main(String[] args) 
    {
        Cyk C =  new Cyk();
        C.checkGrammar();        
    }
}
/*Productions:
S->AB|BC
A->BA|a
B->CC|b
C->AB|a
string: baaba
*/