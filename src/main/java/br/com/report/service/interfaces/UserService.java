package br.com.report.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.report.entity.User;

public interface UserService {

    User toSave(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

}
