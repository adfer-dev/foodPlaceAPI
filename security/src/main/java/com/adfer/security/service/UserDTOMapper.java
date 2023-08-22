package com.adfer.security.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.adfer.security.model.User;
import com.adfer.security.model.UserDTO;

@Service
public class UserDTOMapper implements Function<User, UserDTO>{

	@Override
	public UserDTO apply(User user) {
		return new UserDTO(
				user.getId(), 
				user.getFirstName(), 
				user.getEmail()
		);
	}

}
