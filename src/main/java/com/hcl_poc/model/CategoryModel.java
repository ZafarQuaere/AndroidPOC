package com.hcl_poc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="categoy_table")
public class CategoryModel {

	@Id
	int id;
	@NotBlank
	String categoryname;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	//List<SubCategoryModel> subcategory_list;
//	public List<SubCategoryModel> getSubcategory_list() {
//		return subcategory_list;
//	}
//	public void setSubcategory_list(List<SubCategoryModel> subcategory_list) {
//		this.subcategory_list = subcategory_list;
//	}
	

}
