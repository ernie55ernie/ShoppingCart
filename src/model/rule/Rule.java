/**
 * 
 */
package model.rule;

import java.util.List;

import model.cart.ShoppingCart;
import model.entity.Customer;
import model.entity.Product;

/**
 * Data structure to hold the inference rule from {@link ShoppingCart} data. {@link Customer} and
 *  items been bought are the antecedent of the {@link Rule}, and the result list is the consequent
 *  {@link Product} list. 
 * @author ernie
 *
 */
public class Rule {

	/**
	 * {@link Customer} is the people that bought this {@link ShoppingCart}.
	 */
	private Customer customer;
	
	/**
	 * List of {@link Product} as the antecedent of the rule.
	 */
	private List<Product> antecedent;
	
	/**
	 * List of {@link Product} as the consequent of the rule.
	 */
	private List<Product> consequent;
	
	/**
	 * 
	 */
	private ShoppingCart shoppingCart;
	
	/**
	 * @param customer
	 * @param antecedent
	 * @param consequent
	 */
	public Rule(Customer customer, List<Product> antecedent, List<Product> consequent){
		this.customer = customer;
		this.antecedent = antecedent;
		this.consequent = consequent;
	}
	
	/**
	 * @return the shoppingCart
	 */
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * @param shoppingCart the shoppingCart to set
	 */
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	/**
	 * @param sb
	 * @return
	 */
	public String antecedentString(){
		String string = customer.toString();
		for(Product product: antecedent){
			string = string + ',' + product;
		}
		return string;
	}
	
	/**
	 * @return
	 */
	public String consequentString(){
		String string = consequent.get(0).toString();
		for(int i = 1; i < consequent.size(); i++){
			string = string + '+' + consequent.get(i);
		}
		return string;
	}
	
	/**
	 * @return
	 */
	public Customer getCustomer(){
		return customer;
	}
	
	/**
	 * @param product
	 * @return
	 */
	public boolean containProduct(Product product){
		return antecedent.contains(product) || consequent.contains(product);
	}
	
	@Override
	public boolean equals(Object object){
		if(object instanceof Rule){
			Rule rule = (Rule)object;
			return this.customer.equals(rule.customer) &&
					this.antecedent.equals(rule.antecedent) &&
					this.consequent.equals(rule.consequent);
		}return false;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(antecedentString());
		sb.append("->");
		sb.append(consequentString());
		return sb.toString();
	}
}
