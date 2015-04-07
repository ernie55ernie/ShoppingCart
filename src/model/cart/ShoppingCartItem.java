package model.cart;

import model.entity.Product;

/**
 *
 * @author ernie
 */
public class ShoppingCartItem{

	private Product product;
	private Integer quantity;

	public ShoppingCartItem(Product product){
		this.product = product;
		quantity = 1;
	}

	public void addOne(){
		quantity++;
	}

	public void deleteOne(){
		quantity--;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public int getQuantity(){
		return quantity;
	}

	public double getTotal(){
		return this.getQuantity() * product.getPrice().doubleValue();
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.getProduct());
		for(int i = 1; i < this.getQuantity(); i++)
			sb.append(", ").append(this.getProduct());
		return sb.toString();
	}
}