/**
 * 
 */
package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.cart.ShoppingCart;
import model.entity.Customer;
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
	        int r = in.next().toLowerCase().charAt(0);
	        if(r != 'y' && r != 'n')throw new Exception();
	        System.out.println("");
	        System.out.println("");
	        System.out.println("");
	        System.out.println("Results are :");
	        System.out.println("");
	        System.out.println("");
	        int i = 0;
	        List<ShoppingCart> list = new ArrayList<ShoppingCart>();
	        switch(r){
	        case 'n':
	        	for(i = n; i < m + 1; i++){
	        		list.addAll(enumeration.enumerate(productFacade.getList(), num, i));
	        	}
	        	break;
	        case 'y':
	        	for(i = n; i < m + 1; i++){
	        		list.addAll(repeatableEnumeration.enumerate(productFacade.getList(), num,i));
	        	}
	        	break;
	        }
	        System.out.println(list.size() * cus);
	        Object[][] objects = new Object[list.size() * cus][2];
	        List<Customer> listOdCustomer = customerFacade.getList().subList(0, cus);
	        int curc = 0, curs = 0;
	        for(i = 0; i < list.size() * cus; i++){
	        	curc = i / list.size();
	        	curs = i % list.size();
	        	System.out.print("{" + listOdCustomer.get(curc) + "," + list.get(curs) + "}" + (i == list.size() * cus - 1 ? "" : ","));
	        	objects[i][0] = listOdCustomer.get(curc);
	        	objects[i][1] = list.get(curs);
	        }
        	new DataTable(objects);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Wrong input in console mode");
		}
	}
}
