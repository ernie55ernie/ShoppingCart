/**
 * 
 */
package model.rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.cart.ShoppingCart;
import model.entity.Customer;
import model.entity.Product;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;

import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * {@link RuleBase} stores inference {@link Rule} from {@link ShoppingCart}} data using {@link Map}.
 *  Inference rules can be loaded from a file or {@link String}. {@link RuleBase} use the provided 
 *  {@link Customer} and antecedent list of {@link Product} to find result list of
 *  {@link Product}.
 * @author ernie
 *
 */
public class RuleBase {

	private MultiKeyMap<String, Rule> rules;
	
	/**
	 * Initialize map storage.
	 */
	public RuleBase(){
		rules = new MultiKeyMap<String, Rule>();
	}
	
	/**
	 * Add {@link Rule} from {@link File}. Read each line a time and call {@see #addRule(String)}
	 *  for every line of string and transfer it to {@link Rule}.
	 * @param file
	 */
	public void addRules(File file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String string = null;
			while((string = br.readLine()) != null){
				addRule(string);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param string
	 */
	private void addRule(String string){
		// System.out.println(string);
		
		int equal = string.indexOf('=');
		String[] shoppingCartArray = string.substring(equal + 1).split(",");
		int length = shoppingCartArray.length;
		// System.out.println(Arrays.toString(shoppingCartArray));
		if(length < 3) return;
		
		int customerId = Integer.parseInt(shoppingCartArray[0].substring(1)) - 1;
		Customer customer = CustomerFacade.getInstance().getList().get(customerId);
		
		List<Product> productList = ProductFacade.getInstance().getList();
		int currentItem, currentIteration;
		for(currentItem = 1; currentItem < length; currentItem++){
			List<Product> antecedent = new ArrayList<Product>();
			List<Product> consequent = new ArrayList<Product>();
			for(currentIteration = 1; currentIteration < shoppingCartArray.length; currentIteration++){
				antecedent.add(
						productList.get(Integer.parseInt(shoppingCartArray[currentIteration]
								.substring(1)) - 1));
			}
			consequent.add(antecedent.remove(currentItem - 1));
			Rule rule = new Rule(customer, antecedent, consequent);
			rules.put(customer.toString(),
					rule.antecedentString(),
					rule.consequentString(),
					rule);
			// System.out.println(rule);
		}
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Rule rule: rules.values()){
			sb.append(rule);
			sb.append("\n");
		}
		return sb.toString();
	}
}
