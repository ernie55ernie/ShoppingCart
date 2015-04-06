package model.cart;

import java.util.HashSet;
import java.util.Set;

import model.entity.Product;

/**
 *
 * @author ernie
 */
public class ShoppingCart{

	private Set<ShoppingCartItem> items;
	private int num;
	private double total;

	public ShoppingCart(){
		items = new HashSet<ShoppingCartItem>();
		num = 0;
		total = 0;
	}

	public synchronized void addItem(Product product){
		
	}

	public synchronized void update(Product product, String quantity){

	}

	public synchronized Set<ShoppingCartItem> getItems(){
		return items;
	}

	public synchronized int getNumberOfItems(){
		int numberOfItems = 0;
		for(ShoppingCartItem item: items){
			numberOfItems += item.getQuantity();
		}

		return numberOfItems;
	}

	public synchronized double getSubtotal(){
		double subtotal = 0.0;

		for(ShoppingCartItem item: items)
			subtotal += ((Product)item.getProduct()).getPrice().doubleValue() * item.getQuantity();

		return subtotal;
	}

	public synchronized void calculateTotal(String surcharge){
		total = Double.parseDouble(surcharge) + this.getSubtotal();
	}

	public synchronized double getTotal(){
		return total;
	}
	
	public int getNumberOfItem(){
		return num;
	}

	public synchronized void clear(){
		items.clear();
		num = 0;
		total = 0;
	}
}
