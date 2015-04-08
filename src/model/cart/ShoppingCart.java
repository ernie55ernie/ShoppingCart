package model.cart;

import java.util.ArrayList;
import java.util.List;

import model.entity.Product;

/**
 *
 * @author ernie
 */
public class ShoppingCart{

	private List<ShoppingCartItem> items;
	private int num;
	private double total;

	public ShoppingCart(){
		items = new ArrayList<ShoppingCartItem>();
		num = 0;
		total = 0;
	}

	public synchronized void addItem(Product product){

        for(ShoppingCartItem item: items){
        	if(item.getProduct().getId() == product.getId()){
        		item.addOne();
        		return;
        	}
        }

        ShoppingCartItem scItem = new ShoppingCartItem(product);
        items.add(scItem);
	}

	public synchronized void update(Product product, String quantity){
	}

	public synchronized List<ShoppingCartItem> getItems(){
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
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < items.size() - 1; i++)
			sb.append(items.get(i)).append(",");
		sb.append(items.get(items.size() - 1));
		return sb.toString();
	}
}
