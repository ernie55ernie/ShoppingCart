/**
 * 
 */
package controller.strategy;

import java.util.ArrayList;
import java.util.List;

import model.cart.ShoppingCart;
import model.entity.Customer;
import model.entity.Product;

/**
 * @author ernie
 *
 */
public class RepeatableEnum extends EnumStrategy{
    
    
    /* (non-Javadoc)
     * @see controller.strategy.EnumStrategy#enumerate(java.util.List, int, int)
     */
    public Object[][] enumerate(List<Product> in, int num, int n, int m, List<Customer> cus)
    {
    	enumerateList = new ArrayList<ShoppingCart>();
        list = new ArrayList<Product>();
        this.cus = cus;
        this.in = in.subList(0, num);
        for(int j = n; j < m + 1; j++){
            this.n = j;
        	doEnumerate (0, 0, 0);
        }
        
        return genObjectArray();
    }
    
    /**
     * @param level
     * @param current
     * @param size
     */
    public void doEnumerate (int level, int current, int size)
    {	
        if( level == n) {
        	ShoppingCart sc = new ShoppingCart();
        	for(Object product: list){
        		sc.addItem((Product)product);
        	}
        	enumerateList.add(sc);
            return;
        }
        
        for( int i = size; i < in.size(); ++i )
        {
            list.add( in.get(i) );
            doEnumerate(level + 1, i + 1, size);
            list.remove(   list.size() - 1 );
            size++;
        }
    }
}
