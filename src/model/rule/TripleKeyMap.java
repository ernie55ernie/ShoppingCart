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
	private ArrayListMultimap<K, V> ancedentMap;
	
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
		ancedentMap = ArrayListMultimap.create();
		consequentMap = ArrayListMultimap.create();
		list = new ArrayList<V>();
	}
	
	/**
	 * @param customer
	 * @param ancedent
	 * @param consequent
	 * @param value
	 * @return
	 */
	public V put(K customer, K ancedent, K consequent, V value){
		customerMap.put(customer, value);
		ancedentMap.put(ancedent, value);
		consequentMap.put(consequent, value);
		list.add(value);
		return value;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public List<V> getByAntecedent(K key){
		return ancedentMap.get(key);
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
	 * @return
	 */
	public List<V> values(){
		return list;
	}
}
