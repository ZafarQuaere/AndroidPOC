package com.hcl_poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl_poc.model.RecordEntryModel;

public interface RecordEntryRepository extends JpaRepository<RecordEntryModel,String>{

}
