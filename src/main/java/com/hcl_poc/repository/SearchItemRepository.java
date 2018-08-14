package com.hcl_poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl_poc.model.SearchData;

public interface SearchItemRepository extends JpaRepository<SearchData, Integer>{

}
