/**
 * 
 */
package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entity.Product;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;

/**
 * @author user
 *
 */
public class ConsoleCall {
	
	private static ProductFacade productFacade;
	private static CustomerFacade customerFacade;
	private static Enumeration<Product> enumeration = new Enumeration<Product>();
	private static RepeatableEnumeration<Product> repeatableEnumeration = new RepeatableEnumeration<Product>();
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		productFacade = new ProductFacade("src\\model\\resource\\products.txt");
		customerFacade = new CustomerFacade("src\\model\\resource\\customers.txt");
		while(true){
			try{
				consoleMode();
				in.close();
				return;
			}catch(Exception e){
				System.out.println("Wrong input");	
			}
		}
	}
	
	public static void consoleMode(){
		try{
			System.out.print("Please enter the number of products: ");
	        int num=in.nextInt();
			System.out.print("Please enter the number of customers: ");
	        int cus=in.nextInt();
	        System.out.print("Please enter the least count: ");
	        int n = in.nextInt();
	        System.out.print("Please enter the most count: ");
	        int m = in.nextInt();
	        System.out.print("Repeatable (y for yes or n for no): ");
	        int r = in.next().charAt(0);
	        System.out.println("");
	        System.out.println("");
	        System.out.println("");
	        System.out.println("Results are :");
	        System.out.println("");
	        System.out.println("");
	        int i = 0;
	        List<Object[]> list = new ArrayList<Object[]>();
	        switch(r){
	        case 'n':
	        	for(i = n; i < m + 1; i++){
	        		list.addAll(enumeration.enumerate(productFacade.getList(), num, customerFacade.getList(), cus, i));
	        	}
	        	break;
	        case 'y':
	        	for(i = n; i < m + 1; i++){
	        		list.addAll(repeatableEnumeration.enumerate(productFacade.getList(), num, customerFacade.getList(), cus, i));
	        	}
	        	break;
	        }
	        System.out.println("Size: " + list.size());
	        Object[][] objects = new Object[list.size()][2];
	        for(i = 0; i < list.size(); i++){ 
	        	objects[i][0] = list.get(i)[0];
	        	objects[i][1] = list.get(i)[1];
	        }
        	new DataTable(objects);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in console mode");
		}
	}
}
