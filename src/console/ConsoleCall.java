/**
 * 
 */
package console;

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
			System.out.print("Please enter console mode or GUI (c for console, g for GUI):");
			try{
				int c = in.next().charAt(0);
		        System.out.println("");
				switch(c){
				case 'c':
					consoleMode();
					in.close();
					return;
				case 'g':
					guiMode();
					in.close();
					return;
				}
				throw new Exception();
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
	        switch(r){
	        case 'n':
	        	for(i = n; i < m + 1; i++){
	        		enumeration.enumerate(productFacade.getList(), num, customerFacade.getList(), cus, i);
	        	}
	        	break;
	        case 'y':
	        	for(i = n; i < m + 1; i++){
	        		repeatableEnumeration.enumerate(productFacade.getList(), num, customerFacade.getList(), cus, i);
	        	}
	        	break;
	        }
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in console mode");
		}
	}
	
	public static void guiMode(){
		
	}
}
