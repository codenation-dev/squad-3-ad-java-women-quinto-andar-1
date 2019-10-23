package br.com.report.report.service;

import br.com.report.report.model.User;
import br.com.report.report.service.interfaces.UserService;
import br.com.report.report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

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
