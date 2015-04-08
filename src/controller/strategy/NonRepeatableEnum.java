/**
 * 
 */
package controller.strategy;

import java.util.ArrayList;
import java.util.List;

import model.cart.ShoppingCart;
import model.entity.Product;

/**
 * @author ernie
 *
 */
public class NonRepeatableEnum extends EnumStrategy{
    
    public List<ShoppingCart> enumerate(List<Product> in, int num, int n)
    {
    	enumerateList = new ArrayList<ShoppingCart>();
        list = new ArrayList<Product>();
        this.in = in.subList(0, num);
        this.n = n;
        doEnumerate (0, 0);
        
        return enumerateList;
    }
    
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
