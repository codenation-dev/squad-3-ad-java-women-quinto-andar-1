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
    public Log toSave(Log log) {
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
        return null;//logRepository.findLogByEnvironment(environment);
    }

    @Override
    public List<Log> findLogByEnvironmentAndOrderBy(String environment, String orderBy) {

        return null;//logRepository.findLogByEnvironmentAndOrderBy(environment,orderBy);
    }

    @Override
    public List<Log> findLogByEnvironmentAndSearchBy(String environment, String searchBy) {

        return null;//logRepository.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }

    @Override
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(String environment, String orderBy, String searchBy){


        return null; //logRepository.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
    }
}
