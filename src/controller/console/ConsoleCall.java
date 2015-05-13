/**
 * 
 */
package controller.console;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import model.cart.ShoppingCartUtils;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;
import model.rule.Rule;
import model.rule.RuleBase;
import controller.gui.RuleGUI;
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
	private static Object[][] objectArray;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		productFacade = ProductFacade.getInstance();
		customerFacade = CustomerFacade.getInstance();
		while(true){
			try{
				enumerateShoppingList();
				generateRuleList();
				in.close();
				return;
			}catch(Exception e){
				e.printStackTrace();
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
	        
	        System.out.print("\nPlease enter the number of shopping list: ");
	        int numOfShoppingList = in.nextInt();
	        if(numOfShoppingList > objectArray.length)numOfShoppingList = objectArray.length;
	        ConsoleCall.objectArray = ShoppingCartUtils.toTXT(objectArray, "buy1.txt", numOfShoppingList);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in controller.console shopping list mode");
		}
	}
	
	/**
	 * 
	 */
	public static void generateRuleList(){
		RuleBase rb = new RuleBase();
		rb.addRules(new File("buy1.txt"));
		System.out.print(rb.toString());
		new RuleGUI(objectArray, rb);
		/*try{
			System.out.print("Please enter the shopping list of a specific customer: ");
			in.nextLine();
			String shoppingList = in.nextLine();
			String ancedentList = antecedentString(shoppingList);
			List<Rule> list = rb.getByAntecedent(ancedentList);
			String response;
			while(true){
				if(list.size() == 0){
					System.out.println("No suggestion");
					break;
				}
				System.out.print("Possible buying item: " + list.get(0).consequentString() + 
						"\nDoes the customer buy the item (yes or no)? ");
				response = in.nextLine();
				System.out.println("The customer says " + response);
				if(response.equals("no")){
					list.remove(0);
				}else if(response.equals("yes")) break;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in controller.console rule list mode");
		}*/
	}
	
	/**
	 * @param shoppingList
	 * @return
	 */
	private static String antecedentString(String shoppingList){
		shoppingList = shoppingList.substring(1, shoppingList.length() - 1);
		return shoppingList.replace(',', '*');
	}
}
