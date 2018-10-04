package com.hcl_poc.dao;

import java.util.List;

import com.hcl_poc.model.IdentityNoModel;

public class IdentitiesData {
	
	private int status;
	private String message;
	private List<IdentityNoModel> identities;
	
	
	
	
	public List<IdentityNoModel> getIdentities() {
		return identities;
	}
	public void setIdentities(List<IdentityNoModel> identities) {
		this.identities = identities;
	}
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

}
