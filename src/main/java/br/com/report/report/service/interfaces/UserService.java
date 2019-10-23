package br.com.report.report.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.report.report.model.User;

public interface UserService {

    User toSave(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

}
