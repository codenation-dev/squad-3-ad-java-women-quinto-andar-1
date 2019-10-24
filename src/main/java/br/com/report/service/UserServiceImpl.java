package br.com.report.service;

import br.com.report.model.User;
import br.com.report.repository.UserRepository;
import br.com.report.service.interfaces.LogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements LogUser {

	@Autowired
	private UserRepository userRepository;


	@Override
	public User toSave(User user) {

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


	/*public void changeStatus(Long id){
		userRepository.changeStatus(id);
	}*/

}
