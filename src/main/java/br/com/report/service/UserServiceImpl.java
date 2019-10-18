package br.com.report.service;

import br.com.report.model.User;
import br.com.report.repository.UserRepository;
import br.com.report.service.exception.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements br.com.report.service.UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User toSave(User user) {

		Optional<User> optional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));

		if (optional.isPresent()) {
			throw new DuplicateEmailException("E-mail already registered");
		}

		return userRepository.save(user);

	}

	@Override
	public User findById(Long id) {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new DuplicateEmailException("User not found for id " + id);

	}

	@Override
	public List<User> findAll() {

		return this.userRepository.findAll();
	}

}
