package com.springboot.rest.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.rest.application.entity.UserEntity;
import com.springboot.rest.application.exceptions.ErrorMessages;
import com.springboot.rest.application.exceptions.UserServiceException;
import com.springboot.rest.application.helper.Utils;
import com.springboot.rest.application.repository.UserRepository;
import com.springboot.rest.application.service.UserService;
import com.springboot.rest.application.shared.dto.AddressDto;
import com.springboot.rest.application.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("User Already Exist");
		
		for(int i=0;i<user.getAddresses().size();i++){
			AddressDto addresses=user.getAddresses().get(i);
			addresses.setUserDetails(user);
			addresses.setAddressId(utils.generateAddressId(30));
			user.getAddresses().set(i, addresses);
		}

		//UserEntity entity = new UserEntity();
		//BeanUtils.copyProperties(user, entity);
		ModelMapper modelMapper=new ModelMapper();
		UserEntity entity = modelMapper.map(user, UserEntity.class);
		entity.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
		String publicUserId = utils.generateRandomString(30);
		entity.setUserId(publicUserId);
		UserEntity storeduserDetails = userRepository.save(entity);
		UserDto returnValue = modelMapper.map(storeduserDetails, UserDto.class);
		//BeanUtils.copyProperties(storeduserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User Name is Not Avaiable");
		}

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User Name is Not Avaiable");
		}
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String id) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException("User is not present");
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(UserDto user, String id) {
		UserDto dto = new UserDto();
		UserEntity entity = userRepository.findByUserId(id);
		if (entity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		UserEntity savedData = userRepository.save(entity);
		BeanUtils.copyProperties(savedData, dto);
		return dto;
	}

	@Override
	public void deleteUser(String id) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserDto> returnValue = new ArrayList<>();
		if (page > 0)
			page = page - 1;
		Pageable pageable = PageRequest.of(page, limit);
		Page<UserEntity> userPage = userRepository.findAll(pageable);
		List<UserEntity> users = userPage.getContent();
		for (UserEntity entity : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(entity, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}

}
