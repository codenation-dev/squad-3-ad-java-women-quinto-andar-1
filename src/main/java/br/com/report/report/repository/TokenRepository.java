package br.com.report.report.repository;

import br.com.report.report.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findById(long id);
}
