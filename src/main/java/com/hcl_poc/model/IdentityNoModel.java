package com.hcl_poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="identity_no_table")
public class IdentityNoModel {

	@Id
	@NotBlank
	private int identity_no;
	private String user_name;
	
	
	public int getIdentity_no() {
		return identity_no;
	}
	public void setIdentity_no(int identity_no) {
		this.identity_no = identity_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
