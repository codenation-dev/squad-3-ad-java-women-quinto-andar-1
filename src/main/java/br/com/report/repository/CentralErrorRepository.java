package br.com.report.repository;

import br.com.report.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentralErrorRepository extends JpaRepository<User, Long> {

    User findById(long id);
}
