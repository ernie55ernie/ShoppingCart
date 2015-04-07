/**
 * 
 */
package console;

import java.util.ArrayList;
import java.util.List;

import model.cart.ShoppingCart;
import model.entity.Customer;
import model.entity.Product;

/**
 * @author ernie
 *
 */
public class Enumeration<T> {

	private List<ShoppingCart> enumerateList;
	private List<T> in;
	private List<Customer> customers;
	private List<T> list;
	int n;
    
    public void enumerate(List<T> in, int num, List<Customer> customers, int cus, int n)
    {
    	enumerateList = new ArrayList<ShoppingCart>();
        list = new ArrayList<T>();
        this.customers = customers.subList(0, cus);
        this.in = in.subList(0, num);
        this.n = n;
        doEnumerate (0, 0);

        // System.out.println(Arrays.deepToString(enumerateList.toArray()));
        System.out.println("Size: " + enumerateList.size());
        for(Customer customer: this.customers){
        	for(ShoppingCart shoppingCart: enumerateList){
        		System.out.print("{ " + customer + shoppingCart + " },");
        	}
        	System.out.println(" },");
        }
        
    }
    
    public void doEnumerate (int level, int current)
    {	
        if( level == n) {
        	ShoppingCart sc = new ShoppingCart();
        	for(T product: list){
        		sc.addItem((Product)product);
        	}
        	enumerateList.add(sc);
            return;
        }
        
        for( int i = current; i < in.size(); ++i )
        {
            list.add( in.get(i) );
            doEnumerate(level + 1, i + 1);
            list.remove(   list.size() - 1 );
        }
    }
}
