/**
 * 
 */
package model.probability;

import java.util.Comparator;


/**
 * @author ernie
 *
 */
public class ProfitComparator implements Comparator<Probability> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Probability p1, Probability p2) {
		return p2.getProfit() * p2.getProbability() - p1.getProfit() * p1.getProbability() > 0? 1: -1;
	}

}
