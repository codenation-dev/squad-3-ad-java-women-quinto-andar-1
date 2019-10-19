package br.com.report.repository;

import br.com.report.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    User findByEmail(String email);
    User changeStatus(Long id);
}
