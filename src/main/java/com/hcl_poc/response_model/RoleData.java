package com.hcl_poc.dao;

import java.util.List;

import com.hcl_poc.model.RoleModel;


public class RoleData {

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
	private List<RoleModel> roleList;

	public List<RoleModel> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}
}
