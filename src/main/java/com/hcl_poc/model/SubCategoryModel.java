package com.hcl_poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="subcategory_table")
public class SubCategoryModel {

	@Id
	@NotBlank
	int subcat_id;
	@NotBlank
	String subcat_name;
	@NotBlank
	int cat_id;
	public int getSubcat_id() {
		return subcat_id;
	}
	public void setSubcat_id(int subcat_id) {
		this.subcat_id = subcat_id;
	}
	public String getSubcat_name() {
		return subcat_name;
	}
	public void setSubcat_name(String subcat_name) {
		this.subcat_name = subcat_name;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
	
}
