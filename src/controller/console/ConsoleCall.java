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
import model.probability.Probability;
import model.probability.ProbabilityBase;
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
	
	private static Object[][] sl;
	private static RuleBase rb;
	private static ProbabilityBase pb;
	
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
				calculateProbability();
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
	        System.out.println("Please enter the name of the file: ");
	        String fileName = in.next();
	        ShoppingCartUtils.toTXT(objectArray, fileName, numOfShoppingList);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in controller.console shopping list mode");
		}
	}
	
	/**
	 * 
	 */
	public static void generateRuleList(){
		rb = new RuleBase();
		rb.addRules(new File("buy1.csv"));
		sl = ShoppingCartUtils.fromTxt(new File("buy1.csv"));
		// System.out.print(rb.toString());
		// new RuleGUI(rb);
	}

	/**
	 * 
	 */
	public static void calculateProbability(){
		pb = new ProbabilityBase(rb, sl);

		System.out.print("Please enter the condition and the result of the conditional probability: ");
		/** Using Scanner nextLine() method instead of next() method. Because next() will stop input stream
		 *	while space occurs.
		 */
		String conditionalProbability = in.nextLine();
		if(conditionalProbability.contains("|")){
			System.out.println(pb.getByProbability(conditionalProbability));
		}else{
			List<Probability> pl;
			if(conditionalProbability.contains("客"))
				pl = pb.getHighestThreeProduct(conditionalProbability);
			else
				pl = pb.getByCondition(conditionalProbability);	
			for(Probability p: pl){
				System.out.println(p);
			}	
		}
//		List<Probability> pl = pb.getByCondition("{客1,品1}");
//		float total = 0;
//		for(Probability p: pl){
//			total += p.getProbability();
//			System.out.println(p);
//		}
//		System.out.println(total);
		// System.out.println(pb);
	}
}
