package model.cart;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import model.entity.Product;

/**
 *
 * @author ernie
 */
public class ShoppingCart{

	private List<ShoppingCartItem> items;
	private double total;
	private DateTime time;

	public ShoppingCart(){
		items = new ArrayList<ShoppingCartItem>();
		total = 0;
		time = new DateTime(2010 + (int)(5 * Math.random()),
				(int)(12 * Math.random()) + 1,
				(int)(29 * Math.random()) + 1,
				(int)(24 * Math.random()) + 1,
				(int)(60 * Math.random()) + 1);
	}

	/**
	 * @return the time
	 */
	public DateTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(DateTime time) {
		this.time = time;
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

	public synchronized void clear(){
		items.clear();
		total = 0;
	}
	
	public boolean hasProduct(Product product){
		for(ShoppingCartItem item: items){
			if(item.getProduct().equals(product))return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < items.size() - 1; i++)
			sb.append(items.get(i)).append(",");
		if(items.size() >= 1)sb.append(items.get(items.size() - 1));
		return sb.toString();
	}
}
