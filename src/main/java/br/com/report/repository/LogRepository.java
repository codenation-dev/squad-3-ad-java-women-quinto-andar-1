package br.com.report.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.report.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

    Page<Log> findLogByEnvironmentAndLevelContaining(String environment, String level, Pageable pageable);

    Page<Log> findLogByEnvironmentAndTitleContaining(String environment, String Title, Pageable pageable);

    Page<Log> findLogByEnvironmentAndSourceContaining(String environment, String source, Pageable pageable);

    Page<Log> findLogByEnvironment(String environment, Pageable pageable);

}
