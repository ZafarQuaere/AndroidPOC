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
import com.hcl_poc.dao.CategoryRepository;
import com.hcl_poc.dao.IdentityNoRepository;
import com.hcl_poc.dao.RecordEntryRepository;
import com.hcl_poc.dao.RoleRepository;
import com.hcl_poc.dao.SearchItemRepository;
import com.hcl_poc.dao.SubCategoryRepository;
import com.hcl_poc.dao.UserRepository;
import com.hcl_poc.model.CategoryModel;
import com.hcl_poc.model.ChangePasswordModel;
import com.hcl_poc.model.IdentityNoModel;
import com.hcl_poc.model.RecordEntryModel;
import com.hcl_poc.model.RoleModel;
import com.hcl_poc.model.SearchData;
import com.hcl_poc.model.SubCategoryModel;
import com.hcl_poc.model.UserModel;
import com.hcl_poc.response_model.CategoryData;
import com.hcl_poc.response_model.IdentitiesData;
import com.hcl_poc.response_model.ReportsData;
import com.hcl_poc.response_model.ResponseFormat;
import com.hcl_poc.response_model.RoleData;

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
	@Autowired
	IdentityNoRepository identitiesRepo;

	// Register User
	public ResponseFormat registerUser(UserModel data) {
		String email = data.getEmailId();
		// getting all users from database
		ResponseFormat responseTemplate = new ResponseFormat();
		//List<UserModel> userList = repo.findAll();
		List<UserModel> existingUser = repo.findUserByEmailId(email);
		//boolean isEmail = userList.contains(email);
		if(existingUser != null && !existingUser.isEmpty()) {
				responseTemplate.setStatus(0);
				responseTemplate.setMessage("This email is already register ");
				return responseTemplate;
			}
		/*if (isEmail) {
			responseTemplate.setStatus(0);
			responseTemplate.setMessage("This email is already register ");
			return responseTemplate;

		} else {
			UserModel userData = repo.save(data);
			if (userData != null) {
				responseTemplate.setStatus(1);
				responseTemplate.setMessage("You are successfully registered");
				return responseTemplate;
				// return "You have Successfully Registered";
			} else {
				responseTemplate.setStatus(0);
				responseTemplate.setMessage("Registration Failed! Please try again ");
				return responseTemplate;
				// return "Registration Failed";
			}
*/
		else {
			UserModel userData = repo.save(data);
			if (userData != null) {
				responseTemplate.setStatus(1);
				responseTemplate.setMessage("You are successfully registered");
				return responseTemplate;
				// return "You have Successfully Registered";
			} else {
				responseTemplate.setStatus(0);
				responseTemplate.setMessage("Registration Failed! Please try again ");
				return responseTemplate;
				// return "Registration Failed";
			}
		}
	
	}

	// To find all users from DB
	public List<UserModel> getAllUsers() {
		return repo.findAll();
	}

	// Login the existing user
	public ResponseFormat login(String email, String password) {
		List<UserModel> userList = repo.findAll();
		boolean isEmail = userList.contains(repo.getOne(email));
		ResponseFormat responseTemplate = new ResponseFormat();
		if (isEmail) {
			UserModel userData = repo.findById(email).get();
			if (password.trim().equals(userData.getPassword().trim())) {
				responseTemplate.setStatus(1);
				responseTemplate.setMessage("Logged In Successfully");
				return responseTemplate;

			} else {
				responseTemplate.setStatus(0);
				responseTemplate.setMessage("Please enter valid password ");
				return responseTemplate;
			}
		} else {
			responseTemplate.setStatus(0);
			responseTemplate.setMessage("Please enter valid Email ");
			return responseTemplate;

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

			if (data.getEmailId().equals(pswdData.getEmail())
					&& pswdData.getOldPassword().trim().equals(data.getPassword().trim())) {
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
	public ResponseFormat forgetPassword(String id) {
		ResponseFormat response = new ResponseFormat();
		UserModel userModel = repo.findById(id).get();
		if (userModel != null) {
			String password = userModel.getPassword();
			// now send this password to his email id
			response = sendPswdToEmail(id, password);
		} else {
			response.setStatus(0);
			response.setMessage("Please enter valid email");

		}

		return response;
	}

	private ResponseFormat sendPswdToEmail(String recepientEmail, String passwordToBeSent) {
		ResponseFormat response = new ResponseFormat();
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
			response.setStatus(1);
			response.setMessage("Mail sent Successfully");
			System.out.println("Mail sent successfully");
		} catch (MessagingException e) {
			response.setStatus(0);
			response.setMessage(e.getMessage().toString());
			throw new RuntimeException(e);
		}
		return response;
	}

	// To get the roles
	public RoleData getRoles() {

		List<RoleModel> roles = roleRepo.findAll();
		RoleData data = new RoleData();
		if (roles != null) {
			data.setStatus(1);
			data.setMessage("Get All Roles");
			data.setRoleList(roles);

		}

		return data;
	}

	// Get CategoryList
	public CategoryData getCategoryList() {

		List<CategoryModel> catList = catRepo.findAll();
		List<SubCategoryModel> subCatList = subCatRepo.findAll();

		CategoryData data = new CategoryData();
		data.setStatus(1);
		data.setMessage("Category List");
		data.setCatList(catList);
		data.setSubCatList(subCatList);

		return data;

	}

	// Search Items List
	public List<SearchData> getSearchListData() {
		List<SearchData> searchData = searchRepo.findAll();
		return searchData;
	}

	// Enter Record Functionality
	public ResponseFormat enterRecord(RecordEntryModel entryData) {

		String email = entryData.getEmail_id();
		// getting all users from database
		ResponseFormat responseTemplate = new ResponseFormat();
		List<UserModel> userList = repo.findAll();
		boolean isEmail = userList.contains(email);
		/*
		 * if (!isEmail) { responseTemplate.setStatus(0);
		 * responseTemplate.setMessage("Your email is invalid "); return
		 * responseTemplate;
		 * 
		 * } else {
		 */
		RecordEntryModel recordEntryModel = recordRepo.save(entryData);
		if (recordEntryModel != null) {
			responseTemplate.setStatus(1);
			responseTemplate.setMessage("Your entry has been successfully submitted");
			return responseTemplate;
			// return "You have Successfully Registered";
		} else {
			responseTemplate.setStatus(0);
			responseTemplate.setMessage("Your entry is failed! Please try again ");
			return responseTemplate;
			// return "Registration Failed";
		}

		// }

	}

	public ReportsData getAllRecords(String email) {
		ReportsData response = null;
		List<RecordEntryModel> recordList = new ArrayList<RecordEntryModel>();

		List<RecordEntryModel> allData = recordRepo.findAll();
		boolean isEmailExist = allData.contains(email);
		// if (isEmailExist) {
		if (allData != null && (!allData.isEmpty())) {
			for (int i = 0; i < allData.size(); i++) {
				if (allData.get(i).getEmail_id().trim().equals(email)) {
					RecordEntryModel model = allData.get(i);
					recordList.add(model);
				}
			}
		}

		response = new ReportsData();
		response.setStatus(1);
		response.setMessage("ReportList");
		response.setRecordListData(recordList);

		return response;
	}

	// Get all identity numbers
	public IdentitiesData getIdentities() {
		IdentitiesData response = new IdentitiesData();
		;
		List<IdentityNoModel> identities = identitiesRepo.findAll();
		if (identities != null && (!identities.isEmpty())) {
			response.setStatus(1);
			response.setMessage("Identities List ");
			response.setIdentities(identities);
		} else {
			response.setStatus(0);
			response.setMessage("No Data available ");
			response.setIdentities(identities);
		}

		return response;
	}

	// Get Reports according entered item fields.
	public ReportsData getMatchingRecord(@Valid RecordEntryModel data) {
		ReportsData response = new ReportsData();
		List<RecordEntryModel> recordList = null;
		if (data != null) {
			if (data.getEntryId() > 0) {
				recordList = recordRepo.findReportByEntryId(data.getEntryId());
			} else if (data.getName() != null && !data.getName().equals("")) {
				recordList = recordRepo.findReportByName(data.getName());
			} else if (data.getName() != null && !data.getNumber().equals("")) {
				recordList = recordRepo.findReportByNumber(data.getNumber());
			} else if (data.getName() != null && !data.getNumber1().equals("")) {
				recordList = recordRepo.findReportByNumber1(data.getNumber1());
			} else if (data.getName() != null && !data.getNumber2().equals("")) {
				recordList = recordRepo.findReportByNumber2(data.getNumber2());
			} else if (data.getName() != null && !data.getNumber3().equals("")) {
				recordList = recordRepo.findReportByNumber3(data.getNumber3());
			} else if (data.getAlphaNumberData() != null && !data.getAlphaNumberData().equals("")) {
				recordList = recordRepo.findReportByAlphaNumberData(data.getAlphaNumberData());
			}

			if (recordList != null) {
				response.setStatus(1);
				response.setMessage("Report List");
				response.setRecordListData(recordList);
			} else {
				response.setStatus(0);
				response.setMessage("No data available");
				response.setRecordListData(recordList);
			}

		}
		return response;
	}

}
