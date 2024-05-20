package telran.java52.accounting.service;

import telran.java52.accounting.dto.RolesDto;
import telran.java52.accounting.dto.UserDto;
import telran.java52.accounting.dto.UserRegisterDto;
import telran.java52.accounting.dto.UserUpdateDto;

public interface UserAccountService {

	UserDto register(UserRegisterDto userRegisterDto);

	UserDto getUser(String login);

	UserDto updateUser(String login, UserUpdateDto userUpdateDto);

	UserDto deleteUser(String login);

	RolesDto changeRoleList(String login, String role, boolean isAddRole);

	void changePassword(String login, String newPassword);

}
