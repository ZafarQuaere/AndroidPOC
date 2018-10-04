package com.hcl_poc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl_poc.model.SubCategoryModel;
import com.hcl_poc.model.UserModel;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryModel,Integer>{

}
