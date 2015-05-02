/**
 * 
 */
package model.rule;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.cart.ShoppingCart;
import model.cart.ShoppingCartUtils;
import model.entity.Customer;
import model.entity.Product;

/**
 * {@link RuleBase} stores inference {@link Rule} from {@link ShoppingCart}} data using {@link Map}.
 *  Inference rules can be loaded from a file or {@link String}. {@link RuleBase} use the provided 
 *  {@link Customer} and antecedent list of {@link Product} to find result list of
 *  {@link Product}.
 * @author ernie
 *
 */
public class RuleBase {

	private Map<String, Rule> rules;
	
	public RuleBase(){
		rules = new HashMap<String, Rule>();
	}
	
	public void addRule(File file){
		addRule(ShoppingCartUtils.fromTXT(file));
	}
	
	public void addRule(String string){
		
	}
	
	public Rule removeRule(String string){
		return rules.remove(string);
	}
	
	public List<Product> findRule(String string){
		return rules.get(string).getConsequent();
	}
}
