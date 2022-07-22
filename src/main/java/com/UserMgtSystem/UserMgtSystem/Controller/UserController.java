package com.UserMgtSystem.UserMgtSystem.Controller;

import java.security.AccessController;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UserMgtSystem.UserMgtSystem.Entities.User;
import com.UserMgtSystem.UserMgtSystem.Service.UserService;
import com.UserMgtSystem.UserMgtSystem.dto.UserDto;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1")
//@RequestMapping("/api/v1/test")
@Api(value = "API calls")
public class UserController {

	org.slf4j.Logger logger = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	UserService Userservice;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	// @ApiOperation(value="test")
	public List<User> viewAllUsers(@RequestParam(value = "countryId", required = false) Integer countryId) {
		logger.info("WE WANT TO RETURN VIEW ALL");
		return Userservice.viewAllUser(countryId);
	}

	// >>>>>>> Paginated

	@GetMapping(value = "/viewall")
	public Page<User> PagedViewAllUsers(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy)
			throws Exception {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Userservice.pageabledAllUsers(page, sortBy);
	}

	@PostMapping(value = "/create-user")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) throws Exception {
		logger.info("CREATING................................");
		return new ResponseEntity<User>(Userservice.createUser(userDto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/viewByUserid")
	public ResponseEntity<User> viewUserById(@RequestParam String userid) {
		logger.info("VIEW BY USER ID" + userid);
		return new ResponseEntity<User>(Userservice.viewByUserId(userid), HttpStatus.OK);
	}

	@GetMapping(value = "/viewid/{id}")
	public Optional<User> viewUserById(@PathVariable("id") int id) {
		logger.info("VIEW BY USER ID" + id);
		return Userservice.viewById(id);
	}

	@GetMapping(value = "/getusers-bycountry/")
	public List<User> findByCountryId(@RequestParam("countryId") int countrid) {
		logger.info("VIEW BY USER ID");
		return Userservice.findByCountryId(countrid);
	}

	@PatchMapping(value = "/update-user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {
		logger.info("Updating................................");
		return new ResponseEntity<User>(Userservice.updateUser(userId, userDto), HttpStatus.CREATED);
	}

	@PatchMapping(value = "/deactivateUser/")
	public String deactivateUser(@RequestParam("userId") String userId) throws Exception {
		logger.info("Updating................................");
		return Userservice.deactivateUser(userId);
	}

	@GetMapping(value = "/View-active-users")
	public List<User> viewByStatus() {
		boolean status =true;
		logger.info("VIEW BY USER status" + status);
		return Userservice.findActiverUsers(status);
	}

}
