/**
 * 
 */
package console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ernie
 *
 */
public class Enumeration {
	
	private Set<Object> set = new HashSet<Object>();
	
	int n;
    
    public  void enumerate(int n)
    {
        List<Character> list = new ArrayList<Character>();
        List<Character> in = new ArrayList<Character>();
        this.n = n;
        doEnumerate ( in, list, 0, 0);
        
    }
    
    public  void doEnumerate ( List<Character> in, List<Character> list, int level, int current)
    {	
        if( level == n) {
            String string = Arrays.toString(list.toArray());
            System.out.println(string);
            set.add(string);
            return;
        }
        
        for( int i = current; i < in.size(); ++i )
        {
            list.add( in.get(i) );
            doEnumerate( in,   list, level + 1, i + 1);
            list.remove(   list.size() - 1 );
        }
    }
}
