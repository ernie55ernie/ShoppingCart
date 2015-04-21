/**
 * 
 */
package controller.strategy;

import java.util.List;

import model.cart.ShoppingCart;
import model.entity.Customer;
import model.entity.Product;

/**
 * @author ernie
 *
 */
public abstract class EnumStrategy {

	protected List<ShoppingCart> enumerateList;
	protected List<Product> in;
	protected List<Product> list;
	protected List<Customer> cus;
	protected int n;

	/**
	 * Enumerate {@code num} products in a shopping list of size {@code n}
	 * @param in Input list of products
	 * @param num Number of product that will be used in the enumeration
	 * @param n Number of products in a shopping cart 
	 * @return
	 */
	public abstract Object[][] enumerate(List<Product> in, int num, int n, int m, List<Customer> cus);
	
	/**
	 * Using customer and shopping cart list generate a combination {@code Object}
	 *  of two list.
	 * @return
	 */
	public Object[][] genObjectArray(){
		Object[][] objectArray = new Object[enumerateList.size() * cus.size()][2];
        System.out.println(enumerateList.size() * cus.size());
		int i, curc = 0, curs = 0;
        for(i = 0; i < enumerateList.size() * cus.size(); i++){
        	curc = i / enumerateList.size();
        	curs = i % enumerateList.size();
        	System.out.print("{" + cus.get(curc) + "," + enumerateList.get(curs) + "}" + (i == enumerateList.size() * cus.size() - 1 ? "" : ","));
        	objectArray[i][0] = cus.get(curc);
        	objectArray[i][1] = enumerateList.get(curs);
        }
		
		return objectArray;
	}
}
