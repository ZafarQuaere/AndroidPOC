package com.hcl_poc.dao;

import java.util.List;

import com.hcl_poc.model.CategoryModel;
import com.hcl_poc.model.SubCategoryModel;

public class CategoryData {
	
	private int status;
	private String message;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private List<CategoryModel> catList;
	private List<SubCategoryModel> subCatList;
	
	public List<CategoryModel> getCatList() {
		return catList;
	}
	public void setCatList(List<CategoryModel> catList) {
		this.catList = catList;
	}
	public List<SubCategoryModel> getSubCatList() {
		return subCatList;
	}
	public void setSubCatList(List<SubCategoryModel> subCatList) {
		this.subCatList = subCatList;
	}
	
	

}
