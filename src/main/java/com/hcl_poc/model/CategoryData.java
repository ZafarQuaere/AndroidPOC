package com.hcl_poc.model;

import java.util.List;

public class CategoryData {
	
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
