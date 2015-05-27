/**
 * 
 */
package model.rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.cart.ShoppingCart;
import model.cart.ShoppingCartItem;
import model.entity.Customer;
import model.entity.Product;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;

import com.google.common.collect.ArrayListMultimap;

/**
 * {@link RuleBase} stores inference {@link Rule} from {@link ShoppingCart}} data using {@link Map}.
 *  Inference rules can be loaded from a file or {@link String}. {@link RuleBase} use the provided 
 *  {@link Customer} and antecedent list of {@link Product} to find result list of
 *  {@link Product}.
 * @author ernie
 *
 */
public class RuleBase {
	/**
	 * 
	 */
	private ArrayListMultimap<String, Rule> customerMap;
	
	/**
	 * 
	 */
	private ArrayListMultimap<String, Rule> antecedentMap;
	
	/**
	 * 
	 */
	private ArrayListMultimap<String, Rule> consequentMap;
	
	/**
	 * 
	 */
	private Set<Product> products;
	
	/**
	 * 
	 */
	private Map<Customer, Integer> customersOccurance;
	
	/**
	 * 
	 */
	private List<Rule> list;
	
	/**
	 * Initialize map storage.
	 */
	public RuleBase(){
		customerMap = ArrayListMultimap.create();
		antecedentMap = ArrayListMultimap.create();
		consequentMap = ArrayListMultimap.create();
		list = new ArrayList<Rule>();
		products = new HashSet<Product>();
		customersOccurance = new HashMap<Customer, Integer>();
	}
	
	/**
	 * Add {@link Rule} from {@link File}. Read each line a time and call {@see #addRule(String)}
	 *  for every line of string and transfer it to {@link Rule}.
	 * @param file
	 */
	@Deprecated
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
	@Deprecated
	private void addRule(String string){
		
		String[] shoppingCartArray = string.split(",");
		int length = shoppingCartArray.length;
		if(length < 2) return;
		
		int customerId = Integer.parseInt(shoppingCartArray[0].substring(1)) - 1;
		Customer customer = CustomerFacade.getInstance().getList().get(customerId);
		if(customersOccurance.containsKey(customer)){
			customersOccurance.put(customer, customersOccurance.get(customer) + 1);
		}else{
			customersOccurance.put(customer, 1);
		}
		List<Product> productList = ProductFacade.getInstance().getList();
		int currentItem, currentIteration;
		
		for(currentItem = 1; currentItem < length; currentItem++){
			List<Product> antecedent = new ArrayList<Product>();
			List<Product> consequent = new ArrayList<Product>();
			for(currentIteration = 1; currentIteration < shoppingCartArray.length; currentIteration++){
				Product product = productList.get(Integer.parseInt(shoppingCartArray[currentIteration]
						.substring(1)) - 1);
				antecedent.add(product);
				products.add(product);
			}
			consequent.add(antecedent.remove(currentItem - 1));
			Rule rule = new Rule(customer, antecedent, consequent);
			if(!contains(rule)){
				put(customer.toString(),
					rule.antecedentString(),
					rule.consequentString(),
					rule);
			}
		}
	}
	
	/**
	 * @param objectArrays
	 */
	public void addRules(Object[][] objectArrays){
		for(Object[] objectArray: objectArrays){
			addRule(objectArray);
		}
	}
	
	/**
	 * @param objectArrays
	 */
	public void addRule(Object[] objectArray){

		Customer customer = (Customer)objectArray[0];
		ShoppingCart shoppingCart = (ShoppingCart)objectArray[1];
		int length = shoppingCart.getNumberOfItems();
		if(length < 1) return;
		int currentItem, currentIteration;
		List<ShoppingCartItem> items = shoppingCart.getItems();
		
		for(currentItem = 0; currentItem < length; currentItem++){
			List<Product> consequent = new ArrayList<Product>();
			List<Product> antecedent = new ArrayList<Product>();
			/**
			 * This can't be move to the outer for loop because every
			 * rule need a list of product itself.
			 */
			for(currentIteration = 0; currentIteration < length; currentIteration++){
				Product product = items.get(currentIteration).getProduct();
				antecedent.add(product);
				products.add(product);
			}
			
			consequent.add(antecedent.remove(currentItem));
			Rule rule = new Rule(customer, antecedent, consequent);
			rule.setShoppingCart(shoppingCart);
			if(!contains(rule)){
				put(customer.toString(),
					rule.antecedentString(),
					rule.consequentString(),
					rule);
			}
		}
	}
	
	/**
	 * @return
	 */
	public Object[][] getObjectArray(){
		Object[][] objects = new Object[list.size()][2];
		for(int i = 0; i < list.size(); i++){
			Rule rule = list.get(i);
			objects[i][0] = rule.antecedentString();
			objects[i][1] = rule.consequentString();
		}
		return objects;
	}
	
	/**
	 * @param customer
	 * @param antecedent
	 * @param consequent
	 * @param value
	 * @return
	 */
	private Rule put(String customer, String antecedent, String consequent, Rule value){
		customerMap.put(customer, value);
		antecedentMap.put(antecedent, value);
		consequentMap.put(consequent, value);
		list.add(value);
		return value;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<Rule> getByAntecedent(String key){
		return antecedentMap.get(key);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<Rule> getByConsequent(String key){
		return consequentMap.get(key);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<Rule> getByCustomer(String key){
		return customerMap.get(key);
	}
	
	/**
	 * @param antecedentStirng
	 * @return
	 */
	public boolean containsAntecedent(String antecedentString){
		return antecedentMap.containsKey(antecedentString);
	}
	
	/**
	 * @param consequentString
	 * @return
	 */
	public boolean containsConsequent(String consequentString){
		return consequentMap.containsKey(consequentString);
	}
	
	/**
	 * @param v
	 * @return
	 */
	public boolean contains(Rule v){
		return list.contains(v);
	}
	
	/**
	 * @return
	 */
	public List<Rule> values(){
		return list;
	}
	
	/**
	 * @return
	 */
	public Set<Product> products(){
		return products;
	}
	
	/**
	 * @return
	 */
	public Map<Customer, Integer> customers(){
		return customersOccurance;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Rule rule: values()){
			sb.append(rule);
			/*sb.append(' ');
			sb.append(rule.antecedentString());
			sb.append(' ');
			sb.append(rule.consequentString());*/
			sb.append("\n");
		}
		return sb.toString();
	}
}
