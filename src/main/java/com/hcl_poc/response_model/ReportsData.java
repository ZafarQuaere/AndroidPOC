package com.hcl_poc.response_model;

import java.util.List;

import com.hcl_poc.model.RecordEntryModel;

public class ReportsData {
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
	
	public List<RecordEntryModel> recordListData;

	public List<RecordEntryModel> getRecordListData() {
		return recordListData;
	}
	public void setRecordListData(List<RecordEntryModel> recordListData) {
		this.recordListData = recordListData;
	}

	
}
