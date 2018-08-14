package com.hcl_poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl_poc.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String>{

}
