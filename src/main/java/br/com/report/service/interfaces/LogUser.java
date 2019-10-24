package br.com.report.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.report.model.User;

public interface LogUser {

    User toSave(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

}
