/**
 * 
 */
package model.probability;

import java.util.List;

/**
 * @author ernie
 *
 */
public class Probability {
	
	/**
	 * 
	 */
	private List<Object> condition;
	
	/**
	 * 
	 */
	private List<Object> result;
	
	/**
	 * 
	 */
	private float probability;
	
	/**
	 * 
	 */
	private int conditional;
	
	/**
	 * 
	 */
	private int occurance;
	
	/**
	 * @param probability
	 */
	public Probability(List<Object> condition, List<Object> result, float probability){
		this.condition = condition;
		this.result = result;
		this.probability = probability;
	}
	
	/**
	 * @param probability
	 */
	public Probability(List<Object> condition, List<Object> result,int occurance, int conditional){
		this.condition = condition;
		this.result = result;
		this.conditional = conditional;
		this.occurance = occurance;
		this.probability = occurance / (float)conditional;
	}
	
	/**
	 * @return
	 */
	public String getCondition(){
		StringBuffer sb = new StringBuffer().append('{');
		for(int index = 0; index < condition.size(); index++){
			sb.append(condition.get(index));
			if(index != condition.size() - 1)sb.append(',');
		}
		return sb.append('}').toString();
	}
	
	/**
	 * @return
	 */
	public String getResult(){
		StringBuffer sb = new StringBuffer().append('{');
		for(int index = 0; index < result.size(); index++){
			sb.append(result.get(index));
			if(index != result.size() - 1)sb.append(',');
		}
		return sb.append('}').toString();
	}
	
	/**
	 * @return
	 */
	public float getProbability(){
		return probability;
	}
	
	/**
	 * @return
	 */
	public String conditionalString(){
		return getResult() + " | " + getCondition();
	}
	
	@Override
	public String toString(){
		return getResult() + " | " + getCondition() + " = " + occurance + " / " + conditional + " = " + probability;
	}
}
