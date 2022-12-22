package com.te.blogmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.blogmanagement.dto.LoginDto;
import com.te.blogmanagement.dto.PostDto;
import com.te.blogmanagement.dto.UpdatePostDto;
import com.te.blogmanagement.dto.UserDto;
import com.te.blogmanagement.dto.UserUpdateDto;
import com.te.blogmanagement.email.UserRegistrationEmail;
import com.te.blogmanagement.response.ApiResponse;
import com.te.blogmanagement.service.BmService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/public")
public class BmController {
	private final BmService bmService;
	private final UserRegistrationEmail userRegistrationEmail;

	@PostMapping(path = "/userRegistration")
	public ResponseEntity<ApiResponse<Object>> userRegistration(@RequestBody UserDto userDto) {
		Optional<UserDto> registration = bmService.registration(userDto);
		if (registration.isPresent()) {
			userRegistrationEmail.sendingEmail("chandramailsender@gmail.com", userDto.getEmail(),
					"Registration Successfull" + "\n" + "UserName:" + userDto.getMobile() + "\n" + "password:"
							+ userDto.getPasswordHash());
			return ResponseEntity.ok().body(ApiResponse.builder().message("Registration Successfull").build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("Registration Unsuccessfull").build());
	}

	@PostMapping(path = "/login")
	public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginDto loginDto) {
		Boolean isChecked = bmService.login(loginDto);
		if (isChecked) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Login successfull").build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("Login UnSuccessfull").build());

	}

	@PutMapping(path = "/updateUser/{id}")
	public ResponseEntity<ApiResponse<Object>> updateUser(@PathVariable(name = "id") Integer id,
			@RequestBody UserUpdateDto userUpdateDto) {
		Optional<Boolean> updateUser = bmService.updateUser(id, userUpdateDto);
		if (updateUser.isPresent()) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Update User Successfull").build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("Update User UnSuccessfull").build());
	}

	@GetMapping(path = "/userData/{userid}")
	public ResponseEntity<ApiResponse<Object>> userData(@PathVariable(name = "userid") Integer userid) {
		Optional<UserDto> userDataDto = bmService.userData(userid);
		if (userDataDto.isPresent()) {
			return ResponseEntity.ok()
					.body(ApiResponse.builder().message("User Data Is Available").data(userDataDto.get()).build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("User Data Is Available").build());
	}

	@GetMapping(path = "/userDataList")
	public ResponseEntity<ApiResponse<Object>> userDataList() {
		Optional<List<UserDto>> userDataList = bmService.UserDataList();
		if (userDataList.isPresent()) {
			return ResponseEntity.ok()
					.body(ApiResponse.builder().message("User DataList is Available").data(userDataList.get()).build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("User DataList is Not Available").build());
	}

	@DeleteMapping(path = "/deleteUser/{id}")
	public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable(name = "id") Integer id) {
		Optional<Boolean> deleteUser = bmService.deleteUser(id);
		if (deleteUser.isPresent()) {
			return ResponseEntity.ok().body(ApiResponse.builder().message("Delete User Successfull").build());
		}
		return ResponseEntity.ok().body(ApiResponse.builder().message("Delete User UnSuccessfull").build());

	}

	@PostMapping(path = "/postRegistration/{id}")
	public void postRegistration(@PathVariable(name = "id") Integer id, @RequestBody PostDto postDto) {
		bmService.postRegistration(id, postDto);
	}
	
	@PutMapping(path = "/postUpdate/{id}")
	public void postUpdate(@PathVariable(name = "id") Integer id,@RequestBody UpdatePostDto updatePostDto) {
		bmService.updatePost(id,updatePostDto);
	}

}
