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
public class NonRepeatableEnum extends EnumStrategy{
    
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
        	doEnumerate (0, 0);
        }
        
        return genObjectArray();
    }
    
    /**
     * Recursive function that will add one elements into {@code list}.
     * 
     * @param level In the {@code level} of fill the shopping list
     * @param current {@code current} product that will be fill in the list
     */
    public void doEnumerate (int level, int current)
    {	
        if( level == n) {
        	ShoppingCart sc = new ShoppingCart();
        	for(Product product: list){
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
