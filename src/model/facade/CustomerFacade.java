package model.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import model.entity.Customer;

/**
 *
 * @author Ernie
 */
public class CustomerFacade extends AbstractFacade<Customer>{
	private EntityManager em;
	private List<Customer> list;

	protected EntityManager getEntityManager(){
		return em;
	}
	
	public CustomerFacade(){
		super(Customer.class);
	}

	public CustomerFacade(String filename){
		super(Customer.class);
		list = new ArrayList<Customer>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
			int i = 0;
			while(sc.hasNext() && i < 200){
				String e = sc.nextLine();
				list.add(new Customer(i++, e));
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public List<Customer> getList(){
		return list;
	}
}