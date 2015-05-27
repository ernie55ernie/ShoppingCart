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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.entity.Customer;
import model.entity.Product;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;

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
					fw.write(array[integerArray[i]][0] 
							+ "," + array[integerArray[i]][1] + "\n");
				}
			}else{
				for(int i = 0; i < n; i++){
					fw.write(array[i][0] 
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
			// System.out.println(sb);
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object[][] fromTxt(File file){
		ArrayList<Object[]> objectArrayOfArray = new ArrayList<Object[]>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String string;
			List<Customer> customerList = CustomerFacade.getInstance().getList();
			List<Product> productList = ProductFacade.getInstance().getList();
			while((string = br.readLine()) != null){
				
				String[] shoppingCartArray = string.split(",");
				int customerId = Integer.parseInt(shoppingCartArray[0].substring(1)) - 1;
				int currentItem;
				int length = shoppingCartArray.length;
				// System.out.println(Arrays.toString(shoppingCartArray) + length);
				ShoppingCart sc = new ShoppingCart();
				for(currentItem = 1; currentItem < length; currentItem++){
					sc.addItem(productList.get(Integer.parseInt(shoppingCartArray[currentItem]
								.substring(1)) - 1));
				}
				Object[] objectOfArray = new Object[2];
				objectOfArray[0] = customerList.get(customerId);
				objectOfArray[1] = sc;
				objectArrayOfArray.add(objectOfArray);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectArrayOfArray.toArray(new Object[objectArrayOfArray.size()][2]);
	}
	
}
