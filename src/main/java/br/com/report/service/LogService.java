package br.com.report.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.report.model.Log;

public interface LogService {

    Log toSave(Log log);

    Log findById(Long id);

    void toRemove(Long id);

    Log changeStatus(Long id, String status);

    Page<Log> findAll(Pageable pageable);

    Page<Log> findLogByEnvironment(String environment, Pageable pageable);

    Page<Log> findLogByEnvironmentAndSourceContaining(String environment, String source, Pageable pageable);

    Page<Log> findLogByEnvironmentAndLevelContaining(String environment, String level, Pageable pageable);

    Page<Log> findLogByEnvironmentAndTitleContaining(String environment, String title, Pageable paginacao);

}
