/**
 * 
 */
package console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author ernie
 *
 */
public class RepeatableEnumeration {

	private static Set<String> set = new HashSet<String>();
    private static List<Character> list = new ArrayList<Character>();
	
	public static void main (String args[])
    {
		
        System.out.println("Please enter the string whose permutations we need to show ");
        Scanner in = new Scanner(System.in);
        String original=in.nextLine();
        System.out.println("Please enter the count of enumeration ");
        int n = in.nextInt();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Results are :");
        System.out.println("");
        System.out.println("");
        enumerate(original, n);
        in.close();
    }
    
    
    
    public static   void enumerate( String input, int n)
    {
        List<Character> in = new ArrayList<Character>();
        for(char c: input.toCharArray())
        	in.add(c);
        doEnumerate ( in, list, 0, 0, n, 0);
        
        System.out.println(Arrays.deepToString(set.toArray()));
    }
    
    public static    void doEnumerate ( List<Character> in, List<Character> list, int level, int current, int n, int size)
    {	
        if( level == n) {
            Object[] ca = list.toArray();
            set.add(Arrays.toString(ca));
            return;
        }
        
        for( int i = size; i < in.size(); ++i )
        {
            list.add( in.get(i) );
            doEnumerate( in, list, level + 1, i + 1, n, size);
            list.remove(   list.size() - 1 );
            size++;
        }
    }
}
