package model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ernie
 */
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer price;

	private Date lastUpdate;

	private Category category;

	public Product(){}
	
	public Product(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public Product(Integer id, String name, Integer price, Date lastUpdate){
		this.id = id;
		this.name = name;
		this.setPrice(price);
		this.setLastUpdate(lastUpdate);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode(){
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(Object object){
		if(!(object instanceof Product))
			return false;

		Product product = (Product) object;
		if(this.hashCode() != product.hashCode())
			return false;

		return true;
	}

	@Override
	public String toString(){
		//return "Product[id = " + id + ", name = " + name + "]";
		return "Product" + id;
	}
}