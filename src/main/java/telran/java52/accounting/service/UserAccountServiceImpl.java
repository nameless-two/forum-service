package telran.java52.accounting.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dao.AccountingRepository;
import telran.java52.accounting.dto.RolesDto;
import telran.java52.accounting.dto.UserDto;
import telran.java52.accounting.dto.UserRegisterDto;
import telran.java52.accounting.dto.UserUpdateDto;
import telran.java52.accounting.dto.exception.IncorrectRoleException;
import telran.java52.accounting.dto.exception.UserAlreadyExistsException;
import telran.java52.accounting.dto.exception.UserNotFoundException;
import telran.java52.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	final AccountingRepository accountingRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		accountingRepository.findById(userRegisterDto.getLogin()).ifPresent(a -> {
			throw new UserAlreadyExistsException();
		});
		;
		UserAccount user = modelMapper.map(userRegisterDto, UserAccount.class);
		String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
		user.setPassword(password);
		accountingRepository.save(user);
		user = accountingRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		return modelMapper.map(accountingRepository.findById(login).orElseThrow(UserNotFoundException::new),
				UserDto.class);
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
		UserAccount user = accountingRepository.findById(login).orElseThrow(UserNotFoundException::new);
		boolean res;
		try {
			if (isAddRole)
				res = user.addRole(role);
			else
				res = user.deleteRole(role);
		} catch (Exception e) {
			throw new IncorrectRoleException();
		}
		if (res)
			user = accountingRepository.save(user);
		return modelMapper.map(user.getRoles(), RolesDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount user = accountingRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		user.setPassword(password);
		accountingRepository.save(user);
	}

}
