package br.com.report.service.interfaces;

import br.com.report.model.Log;

import java.util.List;
import java.util.Optional;

public interface LogServiceInterface {

    Log toSave(Log log);

    Optional<Log> findById(Long id);


    void changeStatus(Log log);

    List<Log> findAll();

    List<Log> findLogByEnvironment(String environment);

    List<Log> findLogByEnvironmentAndOrderBy(String environment, String orderBy);

    List<Log> findLogByEnvironmentAndSearchBy(String environment, String searchBy);

    List<Log> findLogByEnvironmentAndOrderByAndSearchBy(String environment, String orderBy, String searchBy);

}
