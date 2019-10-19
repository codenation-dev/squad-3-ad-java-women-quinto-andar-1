package br.com.report.service;

import br.com.report.model.User;
import br.com.report.repository.UserRepository;
import br.com.report.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	//@Autowired
	//public UserService(UserRepository userRepository) {
	//	this.userRepository = userRepository;
	//}

	@Override
	public User toSave(User user) {

	//	Optional<User> optional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));

	//	if (optional.isPresent()) {
	//		throw new DuplicateEmailException("E-mail already registered");
	//	}

		return userRepository.save(user);

	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
//		if (optional.isPresent()) {
//			return optional.get();
//		}
//
//		throw new DuplicateEmailException("User not found for id " + id);

	}

	@Override
	public List<User> findAll() {

		return this.userRepository.findAll();
	}


	public void changeStatus(Long id){
		userRepository.changeStatus(id);
	}

}
