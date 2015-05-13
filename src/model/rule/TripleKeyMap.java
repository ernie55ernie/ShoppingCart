/**
 * 
 */
package model.rule;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;

/**
 * @author ernie
 *
 */
public class TripleKeyMap<K, V> {
	/**
	 * 
	 */
	private ArrayListMultimap<K, V> customerMap;
	
	/**
	 * 
	 */
	private ArrayListMultimap<K, V> antecedentMap;
	
	/**
	 * 
	 */
	private ArrayListMultimap<K, V> consequentMap;
	
	/**
	 * 
	 */
	private List<V> list;
	
	/**
	 * 
	 */
	public TripleKeyMap(){
		customerMap = ArrayListMultimap.create();
		antecedentMap = ArrayListMultimap.create();
		consequentMap = ArrayListMultimap.create();
		list = new ArrayList<V>();
	}
	
	/**
	 * @param customer
	 * @param antecedent
	 * @param consequent
	 * @param value
	 * @return
	 */
	public V put(K customer, K antecedent, K consequent, V value){
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
	public List<V> getByAntecedent(K key){
		return antecedentMap.get(key);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<V> getByConsequent(K key){
		return consequentMap.get(key);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<V> getByCustomer(K key){
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
	
	public boolean contains(V v){
		return list.contains(v);
	}
	
	/**
	 * @return
	 */
	public List<V> values(){
		return list;
	}
}
