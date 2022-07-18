package com.UserMgtSystem.UserMgtSystem.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
import com.UserMgtSystem.UserMgtSystem.Entities.User;
import com.UserMgtSystem.UserMgtSystem.Exception.BadRequestException;
import com.UserMgtSystem.UserMgtSystem.Repos.CountryRepository;
import com.UserMgtSystem.UserMgtSystem.Repos.UserRepository;
import com.UserMgtSystem.UserMgtSystem.Service.UserService;
import com.UserMgtSystem.UserMgtSystem.dto.UserDto;
import com.UserMgtSystem.UserMgtSystem.utils.Validators;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repos;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	Validators validate;

	@Override
	public List<User> viewAllUser(Integer countryId) {
		return countryId == null ? (List<User>) repos.findAll() : repos.findByCountryId(countryId);
	}

	@Override
	public User createUser(UserDto userDto) throws Exception {
		validate.validateUserRequest(userDto);
		User ValEmail = repos.findByEmail(userDto.getEmail());
		if (ValEmail == null) {

			UUID uuid = UUID.randomUUID();
			String userid = uuid.toString();
			User user = new User();
			user.setUserId(userid);
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setPhone(userDto.getPhone());
			user.setSex(userDto.getSex());
			user.setCreatedAt(LocalDate.now());
			user.setUpdatedAt(LocalDate.now());
			user.setStatus(true);
			Optional<Country> country = countryRepo.findById(userDto.getCountryId());
			if (country.isPresent()) {
				user.setCountry(country.get());
			}
			return repos.save(user);
		}
		throw new BadRequestException("400", "EMAIL ALREADY EXIST");
	}

	public User viewByUserId(String userid) {
		// TODO Auto-generated method stub
		return repos.findByUserId(userid);
	}

	public Optional<User> viewById(int id) {
		// TODO Auto-generated method stub
		return repos.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByCountryId(int countrid) {
		return repos.findByCountryId(countrid);
	}

	@Override
	public User updateUser(String userId, UserDto userDto) {
		User user = repos.findByUserId(userId);
		if (user != null) {
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setPhone(userDto.getPhone());
			user.setUpdatedAt(LocalDate.now());
			if (userDto.getCountryId() != null) {
				Optional<Country> country = countryRepo.findById(userDto.getCountryId());
				if (country.isPresent()) {
					user.setCountry(country.get());
				}
			}
			return repos.save(user);
		} else {
			throw new BadRequestException("400", "User Id" + userId + " not found");
		}
	}

//Mr Thom pagination
	@Override
	public Page<User> pageabledAllUsers(Optional<Integer> page, Optional<String> sortBy) throws Exception {
		return repos.findAll(PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("id")));
	}

	@Override
	public String deactivateUser(String userId) throws Exception {
		User user = repos.findByUserId(userId);
		String email = "";
		if (user != null) {
			email = user.getEmail();
			user.setStatus(false);
			return "User with Email: " + email + " has been successfully deactivated ";
		}
		throw new BadRequestException("400", "UserId does not exist");

	}

}