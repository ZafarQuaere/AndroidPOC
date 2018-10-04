package com.hcl_poc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl_poc.model.IdentityNoModel;
import com.hcl_poc.model.RoleModel;
import com.hcl_poc.model.UserModel;

@Repository
public interface IdentityNoRepository extends JpaRepository<IdentityNoModel,Integer>{

}
