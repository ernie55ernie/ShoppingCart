package model.facade;

import javax.persistence.EntityManager;

import model.entity.Product;

/**
 *
 * @author Ernie
 */
public class ProductFacade extends AbstractFacade<Product>{
	private EntityManager em;

	protected EntityManager getEntityManager(){
		return em;
	}

	public ProductFacade(){
		super(Product.class);
	}
}