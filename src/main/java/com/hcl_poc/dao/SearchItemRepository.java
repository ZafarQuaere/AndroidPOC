package com.hcl_poc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl_poc.model.SearchData;

@Repository
public interface SearchItemRepository extends JpaRepository<SearchData, Integer>{

}
