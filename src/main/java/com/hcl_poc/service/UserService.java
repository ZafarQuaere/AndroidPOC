package com.hcl_poc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl_poc.constant.Constants;
import com.hcl_poc.model.CategoryData;
import com.hcl_poc.model.CategoryModel;
import com.hcl_poc.model.ChangePasswordModel;
import com.hcl_poc.model.RecordEntryModel;
import com.hcl_poc.model.RoleModel;
import com.hcl_poc.model.SearchData;
import com.hcl_poc.model.SubCategoryModel;
import com.hcl_poc.model.UserModel;
import com.hcl_poc.repository.CategoryRepository;
import com.hcl_poc.repository.RecordEntryRepository;
import com.hcl_poc.repository.RoleRepository;
import com.hcl_poc.repository.SearchItemRepository;
import com.hcl_poc.repository.SubCategoryRepository;
import com.hcl_poc.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	@Autowired
	RoleRepository roleRepo;

	@Autowired
	CategoryRepository catRepo;
	@Autowired
	SubCategoryRepository subCatRepo;
	@Autowired
	SearchItemRepository searchRepo;
	@Autowired
	RecordEntryRepository recordRepo;

	// Register User
	public String registerUser(UserModel data) {
		UserModel save = repo.save(data);
		if (save != null) {
			return "You have Successfully Registered";
		} else {
			return "Registration Failed";
		}

	}

	// To find all users from DB
	public List<UserModel> getAllUsers() {
		return repo.findAll();
	}

	// Login the existing user
	public String login(String email, String password) {
		List<UserModel> userList = repo.findAll();
		boolean isEmail = userList.contains(repo.getOne(email));

		if (isEmail) {
			UserModel userData = repo.findById(email).get();
			if (password.trim().equals(userData.getPassword().trim())) {
				return "Logged In Successfully" + email + ": " + password + ">>>>>>" + userData.getEmailId() + ":"
						+ userData.getPassword();

			} else
				return "Login failed. Please enter valid password " + email + ": " + password + ">>>>>>"
						+ userData.getEmailId() + ":" + userData.getPassword();
		} else {
			return "Email does not exist ";
		}
	}

	// update user
	public String updateUser(String email, @Valid UserModel userData) {
		List<UserModel> userList = repo.findAll();
		boolean isEmail = userList.contains(repo.getOne(email));
		if (isEmail) {
			UserModel data = repo.findById(email).get();

			for (int i = 0; i < userList.size(); i++) {
				if (email.trim().equals(data.getEmailId().trim())) {
					repo.save(userData);
					return "User Data updated Successfully";
				}
			}

		} else {
			return "Please Enter valid email id ";
		}

		return "User Data updated Successfully";
	}

	// Change user password
	
	public String changePassword(@Valid ChangePasswordModel pswdData) {
		List<UserModel> userList = repo.findAll();
		boolean isEmail = userList.contains(repo.getOne(pswdData.getEmail()));
		if (isEmail) {
			UserModel data = repo.findById(pswdData.getEmail()).get();

			if (data.getEmailId().equals(pswdData.getEmail())&& pswdData.getOldPassword().trim().equals(data.getPassword().trim())) {
				data.setPassword(pswdData.getConfirmPassword());
				repo.save(data);
				return "Password changed successfully";

			} else {
				return "Please Enter valid email or password ";
			}

		} else {
			return "Please Enter valid email id ";
		}

	}

	// Delete user by their id
	public String deleteUser(String email) {
		List<UserModel> userList = repo.findAll();
		boolean isEmail = userList.contains(repo.getOne(email));
		if (isEmail) {
			UserModel registerModel = repo.findById(email).get();
			repo.delete(registerModel);
			return "User Deleted Successfully ";
		} else {
			return "Please Enter valid email id ";
		}
	}

	// Get the single User
	public UserModel getUserById(String id) {

		return repo.findById(id).get();
	}

	// Send Password to the users email id
	public String forgetPassword(String id) {
		String response = "";
		UserModel userModel = repo.findById(id).get();
		if (userModel != null) {
			String password = userModel.getPassword();
			// now send this password to his email id
			response = sendPswdToEmail(id, password);
		} else {
			response = "Please enter valid email ";
		}

		return response;
	}

	private String sendPswdToEmail(String recepientEmail, String passwordToBeSent) {
		String response = "";
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constants.EMAIL, Constants.PASSWORD);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepientEmail));
			message.setSubject(Constants.EMAIL_SUBJECT);
			message.setText(Constants.EMAIL_BODY + " " + passwordToBeSent);
			// send message
			Transport.send(message);
			response = "Mail sent Successfully";
			System.out.println("Mail sent successfully");
		} catch (MessagingException e) {
			response = e.getMessage().toString();
			throw new RuntimeException(e);
		}
		return response;
	}

	// To get the roles
	public List<RoleModel> getRoles() {

		return roleRepo.findAll();
	}

	// Get CategoryList
	public CategoryData getCategoryList() {

		List<CategoryModel> catList = catRepo.findAll();
		List<SubCategoryModel> subCatList = subCatRepo.findAll();

		CategoryData data = new CategoryData();
		data.setCatList(catList);
		data.setSubCatList(subCatList);

		return data;

	}

	// Search Items List

	public List<SearchData> getSearchListData() {
		List<SearchData> searchData = searchRepo.findAll();
		return searchData;
	}

	public String enterRecord(RecordEntryModel entryData) {
		String response = "";
		RecordEntryModel recordEntryModel = recordRepo.save(entryData);
		if (recordEntryModel != null) {
			response = "Record is saved successfully";
		} else {
			response = "Record saved is failed ! Plz try again.";
		}
		return response;
	}
	

	public List<RecordEntryModel> getAllRecords(String email) {
		List<RecordEntryModel> recordList = new ArrayList<RecordEntryModel>();

		List<RecordEntryModel> allData = recordRepo.findAll();
		boolean isEmailExist = allData.contains(email);
		//if (isEmailExist) {
			if (allData != null && (!allData.isEmpty())) {
				for (int i = 0; i < allData.size(); i++) {
					if (allData.get(i).getEmail_id().trim().equals(email)) {
						RecordEntryModel model = allData.get(i);
						recordList.add(model);
					}
				}
			}
	
//		} else {
//			return null;
//		}
		return recordList;
	}

}
