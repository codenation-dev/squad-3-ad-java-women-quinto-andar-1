package br.com.report.repository;

import br.com.report.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    User findByEmail(String email);
}
