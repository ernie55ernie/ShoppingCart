package model.facade;

import javax.persistence.EntityManager;

import model.entity.Customer;

/**
 *
 * @author Ernie
 */
public class CustomerFacade extends AbstractFacade<Customer>{
	private EntityManager em;

	protected EntityManager getEntityManager(){
		return em;
	}

	public CustomerFacade(){
		super(Customer.class);
	}
}