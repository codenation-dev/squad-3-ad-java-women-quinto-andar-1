package br.com.report.service.interfaces;

import br.com.report.entity.Log;

import java.util.List;
import java.util.Optional;

public interface LogServiceInterface {

    Log addLog(Log log);

    Optional<Log> findById(Long id);

    void changeStatus(Log log);

    List<Log> findAll();

    List<Log> findLogByEnvironment(String environment);

    List<Log> findLogByEnvironmentAndOrderByLevel(String environment);

    List<Log> findLogByEnvironmentAndOrderByEvent(String environment);

    List<Log> findLogByEnvironmentAndSearchBy(String environment, String searchBy);

    List<Log> findLogByEnvironmentAndSearchByAndOrderByLevel(String environment, String searchBy);

    List<Log> findLogByEnvironmentAndSearchByAndOrderByEvent(String environment, String searchBy);

}
