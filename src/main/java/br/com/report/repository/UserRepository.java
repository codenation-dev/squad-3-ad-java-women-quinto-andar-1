package br.com.report.repository;

import br.com.report.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Boolean existsByLogin(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByToken(String token);

    Optional<User> findByLoginOrEmail(String login, String email);

    Optional<User> findFirstByOrderById();

}
