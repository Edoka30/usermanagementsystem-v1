package com.UserMgtSystem.UserMgtSystem.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.UserMgtSystem.UserMgtSystem.Exception.BadRequestException;
import com.UserMgtSystem.UserMgtSystem.dto.UserDto;

@Component
public class Validators {
	
	public static boolean isNullOrEmptyString(String stringValue) {
        return (null == stringValue || stringValue.trim().equals(""));
    }
public void validateUserRequest(UserDto userDto) {
	
	if(isNullOrEmptyString(userDto.getFirstName())) {
		throw new BadRequestException("400", "Firstname cannot be null or Empty");
	}
	if(userDto.getLastName() ==null  || userDto.getLastName().trim().isEmpty()) {
		throw new BadRequestException("400", "LastName cannot be null or Empty");
	}
	if(userDto.getEmail() ==null  || userDto.getEmail().trim().isEmpty()) {
		throw new BadRequestException("400", "Email cannot be null or Empty");
	}
	if(!isValidEmail(userDto.getEmail())) {
		throw new BadRequestException("400", "Please provide a Valid Email");
		
	}
}

public static boolean isValidEmail(String email) {
    String email_pattern = "^[a-zA-Z0-9_#$%&’+/=?^.-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(email_pattern);
    Matcher match = pattern.matcher(email);
    return match.matches();
}
}
