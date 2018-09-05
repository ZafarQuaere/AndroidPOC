package com.hcl_poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl_poc.dao.CategoryData;
import com.hcl_poc.dao.ResponseFormat;
import com.hcl_poc.dao.RoleData;
import com.hcl_poc.model.CategoryModel;
import com.hcl_poc.model.RecordEntryModel;
import com.hcl_poc.model.RoleModel;
import com.hcl_poc.model.SearchData;
import com.hcl_poc.model.UserModel;
import com.hcl_poc.service.UserService;

@RestController
@RequestMapping("/poc")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/register")
	public ResponseFormat register(@Valid @RequestBody UserModel data) {
		ResponseFormat response = service.registerUser(data);
		return response;
	}

	@GetMapping("/users")
	public List<UserModel> getAllUsers() {
		List<UserModel> allUsers = service.getAllUsers();
		return allUsers;
	}

	@GetMapping("/login/{email}/{password}")
	public ResponseFormat login(@PathVariable(value = "email") String email,
			@PathVariable(value = "password") String password) {
		ResponseFormat response = service.login(email, password);
		return response;

	}

	@PutMapping("/user/{id}")
	public String updateUserById(@PathVariable(value = "id") String id, @Valid @RequestBody UserModel userData) {
		String response = service.updateUser(id, userData);
		return response;
	}

	@GetMapping("/roles")
	public RoleData getRoles() {
		RoleData roleList = service.getRoles();
		return roleList;
	}

	@GetMapping("/getCategories")
	public CategoryData getCategories() {
		CategoryData catList = service.getCategoryList();
		return catList;
	}

	// find user by id
	@GetMapping("/user/{id}")
	public UserModel findUser(@PathVariable(value = "id") String id) {
		UserModel user = service.getUserById(id);
		if (user == null)
			return null;

		return user;
	}

	// find user by id
	@GetMapping("/forgetpswd/{id}")
	public ResponseFormat forgetPswd(@PathVariable(value = "id") String id) {
		ResponseFormat response = service.forgetPassword(id);
		return response;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable(value = "id") String id) {

		String response = service.deleteUser(id);
		return response;
	}
	
	
	@PostMapping("/recordEntry")
	public ResponseFormat enterRecord(@Valid @RequestBody RecordEntryModel entryData) {
		
		ResponseFormat response = service.enterRecord(entryData);
		return response;
	}
	
	@GetMapping("/searchList")
	public List<SearchData> getSearchData(){
		List<SearchData> searchList = service.getSearchListData();
		return searchList;
	}

	@GetMapping("/getRecords/{email}")
	public List<RecordEntryModel> getRecords( @PathVariable(value="email") String email){
			List<RecordEntryModel> recordList = service.getAllRecords(email);
		return recordList;
	}
}
