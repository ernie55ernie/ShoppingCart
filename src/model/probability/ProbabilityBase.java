/**
 * 
 */
package model.probability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.cart.ShoppingCart;
import model.entity.Customer;
import model.entity.Product;
import model.rule.Rule;
import model.rule.RuleBase;

import com.google.common.collect.ArrayListMultimap;

/**
 * @author ernie
 *
 */
public class ProbabilityBase {
	
	/**
	 * 
	 */
	private ArrayListMultimap<String, Probability> conditionMap;
	
	/**
	 * 
	 */
	private ArrayListMultimap<String, Probability> resultMap;
	
	/**
	 * 
	 */
	private Set<Probability> probabilitySet;
	
	/**
	 * 
	 */
	private RuleBase ruleBase;
	
	/**
	 * 
	 */
	private Object[][] shoppingLists;
	
	/**
	 * 
	 */
	private Set<Product> products;
	
	/**
	 * 
	 */
	private Set<Customer> customers;
	
	/**
	 * @param rb
	 */
	public ProbabilityBase(RuleBase ruleBase, Object[][] shoppingLists){
		conditionMap = ArrayListMultimap.create();
		resultMap = ArrayListMultimap.create();
		this.probabilitySet = new HashSet<Probability>();
		this.ruleBase = ruleBase;
		this.products = ruleBase.products();
		this.customers = ruleBase.customers().keySet();
		this.shoppingLists = shoppingLists;
		addProductCondition();
		addCustomerCondition();
		
		addCustomerOneProductCondition();
		addOneProductCustomerCondition();
	}
	
	/**
	 * 
	 */
	private void addProductCondition(){
		for(Product product: products){
			List<Object> conditionList = new ArrayList<Object>();
			List<Object> resultList;
			conditionList.add(product);
			int condition = 0;
			int occurance;
			List<Rule> listOfRule = ruleBase.getByConsequent(product.toString());
			condition = listOfRule.size();
			for(Customer customer: customers){
				occurance = 0;
				for(Rule rule: listOfRule){
					if(rule.getCustomer().equals(customer))occurance++;
				}
				resultList = new ArrayList<Object>();
				resultList.add(customer);
				Probability probability = new Probability(conditionList, resultList, occurance, condition);
				conditionMap.put(probability.getCondition(), probability);
				resultMap.put(probability.getResult(), probability);
				probabilitySet.add(probability);
			}
		}
	}
	
	/**
	 * 
	 */
	private void addCustomerCondition(){
		int occurance;
		int condition;
		for(Customer customer: customers){
			List<Object> conditionList = new ArrayList<Object>();
			List<Object> resultList;
			
			conditionList.add(customer);
			condition = ruleBase.customers().get(customer);
			for(Product product: products){
				occurance = 0;
				for(Object[] shoppingList: shoppingLists){
					if(shoppingList[0].equals(customer) 
							&& ((ShoppingCart)shoppingList[1]).hasProduct(product)){
						occurance++;
					}
				}
				resultList = new ArrayList<Object>();
				resultList.add(product);
				Probability probability = new Probability(conditionList, resultList, occurance, condition);
				conditionMap.put(probability.getCondition(), probability);
				resultMap.put(probability.getResult(), probability);
				probabilitySet.add(probability);
			}
		}
	}
	
	/**
	 * 
	 */
	private void addCustomerOneProductCondition(){
		for(Product product: products){
			List<Object> conditionList = new ArrayList<Object>();
			List<Object> resultList;
			conditionList.add(product);
			int condition = 0;
			int occurance;
			List<Rule> listOfRule = ruleBase.getByConsequent(product.toString());
			condition = listOfRule.size();
			for(Customer customer: customers){
				for(Product resultProduct: products){
					occurance = 0;
					if(resultProduct.equals(product)){
						continue;
					}
					for(Rule rule: listOfRule){
						if(rule.getCustomer().equals(customer)
								&& rule.containProduct(resultProduct))occurance++;
					}
					resultList = new ArrayList<Object>();
					resultList.add(customer);
					resultList.add(resultProduct);
					Probability probability = new Probability(conditionList, resultList, occurance, condition);
					conditionMap.put(probability.getCondition(), probability);
					resultMap.put(probability.getResult(), probability);
					probabilitySet.add(probability);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	private void addOneProductCustomerCondition(){
		int occurance;
		int condition;
		for(Customer customer: customers){
			List<Object> conditionList;
			List<Object> resultList;
			
			for(Product product: products){
				conditionList = new ArrayList<Object>();
						conditionList.add(customer);
				conditionList.add(1, product);
				for(Product resultProduct: products){
					if(resultProduct.equals(product))continue;
					occurance = 0;
					condition = 0;
					for(Object[] shoppingList: shoppingLists){
						if(shoppingList[0].equals(customer) 
								&& ((ShoppingCart)shoppingList[1]).hasProduct(product)){
							condition++;
							if(((ShoppingCart)shoppingList[1]).hasProduct(resultProduct)){
								occurance++;
							}
						}
					}
					resultList = new ArrayList<Object>();
					resultList.add(resultProduct);
					Probability probability = new Probability(conditionList, resultList, occurance, condition);
					conditionMap.put(probability.getCondition(), probability);
					resultMap.put(probability.getResult(), probability);
					probabilitySet.add(probability);
					
				}
			}
		}
	}
	
	/**
	 * @param condition
	 * @return
	 */
	public List<Probability> getByCondition(String condition){
		return conditionMap.get(condition);
	}
	
	/**
	 * @param result
	 * @return
	 */
	public List<Probability> getByResult(String result){
		return conditionMap.get(result);
	}
	
	/**
	 * @param probabilityString
	 * @return
	 */
	public Probability getByProbability(String probabilityString){
		for(Probability probability: probabilitySet){
			if(probability.conditionalString().equals(probabilityString)){
				return probability;
			}
		}
		return null;
	}
	
	public List<Probability> getHighestThreeProduct(String customer){
		Collections.sort(this.getByCondition(customer), new ProbabilityComparator());
		List<Probability> sortedThreeProbability = new ArrayList<Probability>();
		sortedThreeProbability.addAll(this.getByCondition(customer).subList(0, 3));
		return sortedThreeProbability;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Probability probability: probabilitySet){
			sb.append(probability);
			sb.append("\n");
		}
		return sb.toString();
	}
}
