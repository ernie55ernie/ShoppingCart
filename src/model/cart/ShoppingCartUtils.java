/**
 * 
 */
package model.cart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ernie
 *
 */
public class ShoppingCartUtils {

	/**
	 * Transfer customers and shopping cart in an array of
	 *  {@code Object} into a txt file.
	 * @param list Input array of customers and shopping cart. 
	 * @param fileName File name for the txt file.
	 */
	public static void toTXT(Object[][] array, String fileName){
		toTXT(array, fileName, array.length);
	}
	
	/**
	 * Transfer customers and shopping cart in an array of
	 *  {@code Object} into a txt file.
	 * @param list Input array of customers and shopping cart. 
	 * @param fileName File name for the txt file.
	 * @param n The shopping list that will be write to the 
	 * file
	 */
	public static void toTXT(Object[][] array, String fileName, int n){
		File file = new File(fileName);
		try {
			FileWriter fw = new FileWriter(file);
			if(n < array.length){
				Integer[] integerArray = randomInt(n, array.length);
				for(int i = 0; i < n; i++){
					fw.write("清單" + (i+1) + "=" + array[integerArray[i]][0] 
							+ "," + array[integerArray[i]][1] + "\n");
				}
			}else{
				for(int i = 0; i < n; i++){
					fw.write("清單" + (i+1) + "=" + array[i][0] 
							+ "," + array[i][1] + "\n");
				}
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param numOfRand
	 * @param upperbound
	 * @return
	 */
	private static Integer[] randomInt(int numOfRand, int upperbound){
		Set<Integer> set = new HashSet<Integer>();
		while(set.size() < numOfRand){
			set.add((int)(Math.random() * upperbound));
		}
		Integer[] integerArray = set.toArray(new Integer[set.size()]);
		return integerArray;
	}
	

	/**
	 * Transfer txt file to string.
	 * @param fileName
	 * @return
	 */
	public static String fromTXT(File file){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuffer sb = new StringBuffer();
			String string;
			while((string = br.readLine()) == null){
				sb.append(string);
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
