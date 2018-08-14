package com.hcl_poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="search_item_table")
public class SearchData {

	@Id
	@NotBlank
	private int search_item_id;
	@NotBlank
	private String search_item_name;
	
	public int getItemId() {
		return search_item_id;
	}
	public void setItemId(int itemId) {
		this.search_item_id = itemId;
	}
	public String getItemName() {
		return search_item_name;
	}
	public void setItemName(String itemName) {
		this.search_item_name = itemName;
	}
	
	

}

