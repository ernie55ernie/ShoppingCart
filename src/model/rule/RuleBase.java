/**
 * 
 */
package model.rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
	
	/**
	 * Initialize map storage.
	 */
	public RuleBase(){
		rules = new HashMap<String, Rule>();
	}
	
	/**
	 * Add {@link Rule} from {@link File}. Read each line a time and call {@see #addRule(String)}
	 *  for every line of string and transfer it to {@link Rule}.
	 * @param file
	 */
	public void addRules(File file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String string;
			while((string = br.readLine()) == null){
				addRule(string);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addRule(String string){
		
	}
	
	public Rule removeRule(String string){
		return rules.remove(string);
	}
	
	public List<Product> findRule(String string){
		return rules.get(string).getConsequent();
	}
}
