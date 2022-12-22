package com.te.blogmanagement.service;

import java.util.List;
import java.util.Optional;

import com.te.blogmanagement.dto.LoginDto;
import com.te.blogmanagement.dto.PostDto;
import com.te.blogmanagement.dto.UpdatePostDto;
import com.te.blogmanagement.dto.UserDto;
import com.te.blogmanagement.dto.UserUpdateDto;

public interface BmService {

	Optional<UserDto> registration(UserDto userDto);

	void postRegistration(Integer id, PostDto postDto);

	Boolean login(LoginDto loginDto);

	Optional<Boolean> updateUser(Integer id, UserUpdateDto userUpdateDto);

	Optional<Boolean> deleteUser(Integer id);

	Optional<UserDto> userData(Integer userid);

	Optional<List<UserDto>> UserDataList();

	void updatePost(Integer id, UpdatePostDto updatePostDto);

}
