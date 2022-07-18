package com.UserMgtSystem.UserMgtSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.UserMgtSystem.UserMgtSystem.Entities.User;
import com.UserMgtSystem.UserMgtSystem.dto.UserDto;

public interface UserService {

	public List<User> viewAllUser(Integer countryId);

	public User createUser(UserDto userDto) throws Exception;

	public User viewByUserId(String urserid);

	public Optional<User> viewById(int id);

	public User findByEmail(String email);

	public List<User> findByCountryId(int countrid);

	public User updateUser(String userId, UserDto userDto);
	public Page<User> pageabledAllUsers(Optional<Integer> page, Optional<String> sortBy) throws Exception;
	public String deactivateUser(String userId) throws Exception;

}
