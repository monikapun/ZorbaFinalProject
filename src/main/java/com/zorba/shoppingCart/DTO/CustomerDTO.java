package com.zorba.shoppingCart.DTO;

import java.util.List;

public class CustomerDTO {

	private String custName;
	private Long mobNumber;
	private String country;
	private List<ItemsDTO> items;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Long getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(Long mobNumber) {
		this.mobNumber = mobNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<ItemsDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemsDTO> items) {
		this.items = items;
	}
}
