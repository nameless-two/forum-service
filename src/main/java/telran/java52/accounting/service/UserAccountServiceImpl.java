package telran.java52.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dao.AccountingRepository;
import telran.java52.accounting.dto.RolesDto;
import telran.java52.accounting.dto.UserDto;
import telran.java52.accounting.dto.UserRegisterDto;
import telran.java52.accounting.dto.UserUpdateDto;
import telran.java52.accounting.dto.exception.UserNotFoundException;
import telran.java52.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	final AccountingRepository accountingRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		UserAccount user = modelMapper.map(userRegisterDto, UserAccount.class);
		accountingRepository.save(user);
		user = accountingRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		return modelMapper.map(accountingRepository.findById(login), UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserUpdateDto userUpdateDto) {
		UserAccount user = accountingRepository.findById(login).orElseThrow(UserNotFoundException::new);
		if (userUpdateDto.getFirstName() != null)
			user.setFirstName(userUpdateDto.getFirstName());
		if (userUpdateDto.getLastName() != null)
			user.setFirstName(userUpdateDto.getLastName());
		user = accountingRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto deleteUser(String login) {
		UserDto user = getUser(login);
		accountingRepository.deleteById(login);
		return user;
	}

	@Override
	public RolesDto changeRoleList(String login, String role, boolean isAddRole) {
		// TODO Auto-generated method stub
		UserAccount user = accountingRepository.findById(login).orElseThrow(UserNotFoundException::new);
		if (isAddRole)
			user.addRole(role);
		else
			user.deleteRole(role);
		user = accountingRepository.save(user);
		return modelMapper.map(user.getRoles(), RolesDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount user = accountingRepository.findById(login).orElseThrow(UserNotFoundException::new);
		if (newPassword != null)
			user.setPassword(newPassword);
		user = accountingRepository.save(user);
	}

}
