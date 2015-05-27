package model.entity;

import java.io.Serializable;

import org.joda.time.LocalDate;

/**
 *
 * @author Ernie
 */
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String email;

	private String phone;

	private String address;

	private String cityRegion;
	
	private LocalDate birthday;

	public Customer(){}

	public Customer(Integer id){
		this.id = id;
	}
	
	public Customer(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public Customer(Integer id, String name, String email, String phone, String address, String cityRegion){
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cityRegion = cityRegion;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the cityRegion
	 */
	public String getCityRegion() {
		return cityRegion;
	}

	/**
	 * @param cityRegion the cityRegion to set
	 */
	public void setCityRegion(String cityRegion) {
		this.cityRegion = cityRegion;
	}
	
	
	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(int year, int month, int day) {
		this.birthday = new LocalDate(year, month, day);
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override public int hashCode(){
		return (id != null ? id.hashCode() : 0);
	}

	@Override
	public boolean equals(Object object){
		if(!(object instanceof Customer)){
			return false;
		}
		Customer customer = (Customer) object;
		if(this.hashCode() != customer.hashCode())
			return false;
		return true;
	}

	@Override 
	public String toString(){
		//return name;
		// return "Customer[id =" + id + ", name = " + name + "]";
		return "客" + (id + 1);
	}
}	