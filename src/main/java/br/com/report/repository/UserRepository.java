package br.com.report.repository;

import br.com.report.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByLoginOrEmail(String username, String email);

    Boolean existsByLogin(String username);

    Boolean existsByEmail(String email);
}
