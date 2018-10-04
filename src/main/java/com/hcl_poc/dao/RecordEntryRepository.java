package com.hcl_poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl_poc.model.RecordEntryModel;

@Repository
public interface RecordEntryRepository extends JpaRepository<RecordEntryModel, Integer> {

	public List<RecordEntryModel> findReportByEntryId(int entryId);

	public List<RecordEntryModel> findReportByName(String name);

	public List<RecordEntryModel> findReportByNumber(String number);

	public List<RecordEntryModel> findReportByNumber1(String number1);

	public List<RecordEntryModel> findReportByNumber2(String number2);

	public List<RecordEntryModel> findReportByNumber3(String number3);

	public List<RecordEntryModel> findReportByAlphaNumberData(String alphaNumberData);

}
