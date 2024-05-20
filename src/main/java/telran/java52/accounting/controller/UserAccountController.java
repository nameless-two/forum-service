package telran.java52.accounting.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dto.RolesDto;
import telran.java52.accounting.dto.UserDto;
import telran.java52.accounting.dto.UserRegisterDto;
import telran.java52.accounting.dto.UserUpdateDto;
import telran.java52.accounting.service.UserAccountService;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserAccountController {

	final UserAccountService accountService;

	@PostMapping("/register")
	public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return accountService.register(userRegisterDto);
	}

	@PostMapping("/login")
	public UserDto login(Principal principal) {
		// TODO
		return accountService.getUser(principal.getName());
	}

	@GetMapping("/user/{user}")
	public UserDto getUser(@PathVariable String user) {
		return accountService.getUser(user);
	}

	@PutMapping("/user/{user}")
	public UserDto updateUser(@PathVariable String user, @RequestBody UserUpdateDto userUpdateDto) {
		return accountService.updateUser(user, userUpdateDto);
	}

	@DeleteMapping("/user/{user}")
	public UserDto deleteUser(@PathVariable String user) {
		return accountService.deleteUser(user);
	}

	@PutMapping("/user/{user}/role/{role}")
	public RolesDto addRole(@PathVariable String user, @PathVariable String role) {
		return accountService.changeRoleList(user, role, true);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public RolesDto deleteRole(@PathVariable String user, @PathVariable String role) {
		return accountService.changeRoleList(user, role, false);
	}

	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
		accountService.changePassword(principal.getName(), newPassword);
	}

}
