/**
 * 
 */
package controller.console;

import java.io.File;
import java.util.Scanner;

import model.cart.ShoppingCartUtils;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;
import model.rule.RuleBase;
import controller.strategy.EnumStrategy;
import controller.strategy.NonRepeatableEnum;
import controller.strategy.RepeatableEnum;

/**
 * @author ernie
 *
 */
public class ConsoleCall {
	
	private static ProductFacade productFacade;
	private static CustomerFacade customerFacade;
	private static EnumStrategy enumerationStrategy;
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		productFacade = ProductFacade.getInstance();
		customerFacade = CustomerFacade.getInstance();
		while(true){
			try{
				// enumerateShoppingList();
				generateRuleList();
				in.close();
				return;
			}catch(Exception e){
				System.out.println("Wrong input");	
			}
		}
	}
	
	/**
	 * Run the console that accept user keyboard input 
	 */
	public static void enumerateShoppingList(){
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
	        int r = in.next().toLowerCase().charAt(0);
	        if(r != 'y' && r != 'n' || num  <= 0 ||
	        		cus <= 0 || n <= 0 || m <= 0 ||
	        		m > num)throw new Exception();
	        System.out.println("");
	        System.out.println("");
	        System.out.println("");
	        System.out.println("Results are :");
	        System.out.println("");
	        System.out.println("");
	        Object[][] objectArray = null;
	        switch(r){
	        case 'n':
	        	enumerationStrategy = new NonRepeatableEnum();
	        	objectArray = enumerationStrategy.enumerate(productFacade.getList()
	        			, num, n, m, customerFacade.getList().subList(0, cus));
	        	break;
	        case 'y':
	        	enumerationStrategy = new RepeatableEnum();
	        	objectArray = enumerationStrategy.enumerate(productFacade.getList()
	        			, num, n, m, customerFacade.getList().subList(0, cus));
	        	break;
	        }
	        
	        System.out.println("\nPlease enter the number of shopping list: ");
	        int numOfShoppingList = in.nextInt();
	        
	        ShoppingCartUtils.toTXT(objectArray, "buy1.txt", numOfShoppingList);
        	// new DataTable(objectArray);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in controller.console mode");
		}
	}
	
	public static void generateRuleList(){
		RuleBase rb = new RuleBase();
		rb.addRules(new File("buy1.txt"));
		System.out.print(rb.toString());
	}
}
