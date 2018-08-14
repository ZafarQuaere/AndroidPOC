package com.hcl_poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="record_entry_table")
public class RecordEntryModel {
	@NotBlank
	private String email_id;
	@Id
	@NotNull
	private int entry_id;
	private String number;
	private String number1;
	private String number2;
	private String number3;
	private String number4;
	private String number5;
	private String number6;
	private String number7;
	private String number8;
	private String number9;
	private String number10;
	private String alphanumber_data;
	private String dropdown1;
	private String dropdown2;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public int getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(int entry_id) {
		this.entry_id = entry_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber1() {
		return number1;
	}
	public void setNumber1(String number1) {
		this.number1 = number1;
	}
	public String getNumber2() {
		return number2;
	}
	public void setNumber2(String number2) {
		this.number2 = number2;
	}
	public String getNumber3() {
		return number3;
	}
	public void setNumber3(String number3) {
		this.number3 = number3;
	}
	public String getNumber4() {
		return number4;
	}
	public void setNumber4(String number4) {
		this.number4 = number4;
	}
	public String getNumber5() {
		return number5;
	}
	public void setNumber5(String number5) {
		this.number5 = number5;
	}
	public String getNumber6() {
		return number6;
	}
	public void setNumber6(String number6) {
		this.number6 = number6;
	}
	public String getNumber7() {
		return number7;
	}
	public void setNumber7(String number7) {
		this.number7 = number7;
	}
	public String getNumber8() {
		return number8;
	}
	public void setNumber8(String number8) {
		this.number8 = number8;
	}
	public String getNumber9() {
		return number9;
	}
	public void setNumber9(String number9) {
		this.number9 = number9;
	}
	public String getNumber10() {
		return number10;
	}
	public void setNumber10(String number10) {
		this.number10 = number10;
	}
	public String getAlphanumber_data() {
		return alphanumber_data;
	}
	public void setAlphanumber_data(String alphanumber_data) {
		this.alphanumber_data = alphanumber_data;
	}
	public String getDropdown1() {
		return dropdown1;
	}
	public void setDropdown1(String dropdown1) {
		this.dropdown1 = dropdown1;
	}
	public String getDropdown2() {
		return dropdown2;
	}
	public void setDropdown2(String dropdown2) {
		this.dropdown2 = dropdown2;
	}
	
	
	
}
