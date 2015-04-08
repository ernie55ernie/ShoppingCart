/**
 * 
 */
package controller.strategy;

import java.util.List;

import model.cart.ShoppingCart;
import model.entity.Product;

/**
 * @author ernie
 *
 */
public abstract class EnumStrategy {

	protected List<ShoppingCart> enumerateList;
	protected List<Product> in;
	protected List<Product> list;
	protected int n;

	public abstract List<ShoppingCart> enumerate(List<Product> in, int num, int n);
}
