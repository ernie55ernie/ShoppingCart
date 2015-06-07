/**
 * 
 */
package controller.console;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import model.cart.ShoppingCartUtils;
import model.entity.Product;
import model.facade.CustomerFacade;
import model.facade.ProductFacade;
import model.probability.Probability;
import model.probability.ProbabilityBase;
import model.rule.RuleBase;
import controller.strategy.EnumStrategy;
import controller.strategy.NonRepeatableEnum;
import controller.strategy.RepeatableEnum;
import controller.utils.Utils;

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
		System.out.print("Please enter the file name: ");
		String fileName = in.nextLine();
		File file = new File(fileName);
		
		sl = ShoppingCartUtils.fromTxt(file);
		
		rb = new RuleBase();
		rb.addRules(sl);
		// System.out.print(rb.toString());
		
	}

	/**
	 * 
	 */
	public static void calculateProbability(){
		//System.out.println()
		/**
		 * Using multithread to cope with big data 
		 */
		LoadProbability loadProbability = new LoadProbability();
		loadProbability.start();

		// new RuleGUI(ShoppingCartUtils.addShoppingListTime(sl), rb, pb);
		InputProbability inputProbability = new InputProbability();
		inputProbability.start();
		
	}
	
	private static class LoadProbability implements Runnable{
		private Thread t; 
		   
		@Override
		public void run() {
			pb = new ProbabilityBase(rb, sl);
		}
		
		public void start()
		   {
		      System.out.println("Start to load the probability");
		      if (t == null)
		      {
		         t = new Thread (this, "Probability");
		         t.start ();
		      }
		   }
	}
	
	private static class InputProbability implements Runnable{
		private Thread t; 
		   
		@Override
		public void run() {
			System.out.print("Open profit mode (y for yes, n for no):");
			char p = in.nextLine().toLowerCase().charAt(0);
			
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
					if(p == 'n'){
						pl = pb.getHighestThreeProduct(conditionalProbability);
					}else{
						for(Product product :rb.products()){
							System.out.println(product + ": " + product.getProfit());
						}
						pl = pb.getHighestThreeProfit(conditionalProbability);
					}
				else
					pl = pb.getByCondition(conditionalProbability);	
				String content = "";
				for(Probability probability: pl){
					String string = probability.toString();
					content += string.substring(0, string.indexOf('|')) + "\n";
					System.out.println(probability);
				}
				/**
				 * Sending e-mail 
				 */
				System.out.print("Please enter your e-mail: ");
				String email = in.nextLine();
				Utils.sendEmail(email, "Recommand products", content);
				Utils.postFBMessage(content);
			}
		}
		
		public void start()
		   {
		      System.out.println("Start to load the probability");
		      if (t == null)
		      {
		         t = new Thread (this, "Input");
		         t.start ();
		      }
		   }
	}
}
