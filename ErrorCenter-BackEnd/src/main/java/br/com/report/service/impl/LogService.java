package br.com.report.service.impl;

import br.com.report.entity.Log;
import br.com.report.repository.*;
import br.com.report.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService implements LogServiceInterface {

    @Autowired
    private LogRepository logRepository;

    @Override
    public Log addLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Optional<Log> findById(Long id) {
        return logRepository.findById(id);
    }

    @Override
    public void changeStatus(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> findLogByEnvironment(String environment) {
        return logRepository.findLogByEnvironment(environment);
    }

    @Override
    public List<Log> findLogByEnvironmentAndOrderByLevel(String environment) {

        return logRepository.findLogByEnvironmentAndOrderByLevel(environment);
    }

    @Override
    public List<Log> findLogByEnvironmentAndOrderByEvent(String environment) {

        return logRepository.findLogByEnvironmentAndOrderByEvent(environment);
    }

    @Override
    public List<Log> findLogByEnvironmentAndSearchBy(String environment, String searchBy) {

        return logRepository.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }

    @Override
    public List<Log> findLogByEnvironmentAndSearchByAndOrderByLevel(String environment, String searchBy){


        return logRepository.findLogByEnvironmentAndSearchByAndOrderByLevel(environment, searchBy);
    }

    @Override
    public List<Log> findLogByEnvironmentAndSearchByAndOrderByEvent(String environment, String searchBy){


        return logRepository.findLogByEnvironmentAndSearchByAndOrderByEvent(environment, searchBy);
    }

}
