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
	 * @return
	 */
	public List<Product> getConsequent(){
		return consequent;
	}
	
	@Override
	public boolean equals(Object object){
		if(object instanceof Rule){
			Rule rule = (Rule)object;
			return this.customer == rule.customer &&
					this.antecedent.equals(rule.antecedent);
		}return false;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(customer);
		sb.append(',');
		for(Product product: antecedent){
			sb.append('*');
			sb.append(product);
		}
		sb.append("->");
		for(Product product: consequent){
			sb.append('*');
			sb.append(product);
		}
		return sb.toString();
	}
}
