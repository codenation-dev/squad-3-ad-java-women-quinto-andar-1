package br.com.report.controller;

import br.com.report.entity.Log;

import br.com.report.service.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogServiceImpl logServiceImpl;


    @PostMapping("/log")
    public Log toSave(@RequestBody Log log){
        return logServiceImpl.toSave(log);
    }

    @GetMapping("/log/{id}")
    public Optional<Log> findById(@PathVariable(value = "id")  Long id){
        return logServiceImpl.findById(id);
    }

    @PutMapping("/log")
    public void changeStatus(@RequestBody Log log){
        logServiceImpl.changeStatus(log);
    }

    @GetMapping("/log")
    public List<Log> findAll() {
        return logServiceImpl.findAll();
    }

    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment) {
        return logServiceImpl.findLogByEnvironment(environment);
    }

    @GetMapping("/log/{environment}/{orderBy}")
    public List<Log> findLogByEnvironmentAndOrderBy(@PathVariable(value = "environment") String environment, @PathVariable(value = "orderBy") String orderBy){
        return logServiceImpl.findLogByEnvironmentAndOrderBy(environment,orderBy);
    }
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment, @PathVariable(value = "searchBy")String searchBy){
        return logServiceImpl.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }

    @GetMapping("/log/{environment}/{orderBy}/{searchBy}")
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(@PathVariable(value = "environment") String environment,@PathVariable(value = "orderBy") String orderBy,@PathVariable(value="searchBy") String searchBy) {
        return logServiceImpl.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
    }




}
