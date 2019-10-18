package br.com.report.repository;

import br.com.report.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findById(long id);
}
