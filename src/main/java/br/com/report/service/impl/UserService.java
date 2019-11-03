package br.com.report.service.impl;

import br.com.report.entity.User;
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

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public void changeStatus(User user){
		 userRepository.save(user);
	}

}
