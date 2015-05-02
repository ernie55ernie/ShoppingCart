package model.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import model.entity.Product;

/**
 *
 * @author Ernie
 */
public class ProductFacade extends AbstractFacade<Product>{
	private EntityManager em;
	private List<Product> list;
	private static ProductFacade productFacade;

	protected EntityManager getEntityManager(){
		return em;
	}
	
    public static ProductFacade getInstance(){
    	if(productFacade == null){
    		synchronized(ProductFacade.class){
    			if(productFacade == null){
    				productFacade = new ProductFacade("src\\model\\resource\\products.txt");
    			}
    		}
    	}
    	return productFacade;
    }
	
	protected ProductFacade(){
		super(Product.class);
	}

	protected ProductFacade(String filename){
		super(Product.class);
		list = new ArrayList<Product>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
			int i = 0;
			sc.nextLine();
			while(sc.hasNext() && i < 200){
				String e = sc.nextLine();
				list.add(new Product(i++, e.substring(0, e.indexOf('\t'))));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getList(){
		return list;
	}
}