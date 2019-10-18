package br.com.report.service;

import java.util.List;

import br.com.report.model.User;

public interface UserService {

    User toSave(User user);

    User findById(Long id);

    List<User> findAll();

}
