package com.hcl_poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl_poc.model.RoleModel;
import com.hcl_poc.model.UserModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel,Integer>{

}
