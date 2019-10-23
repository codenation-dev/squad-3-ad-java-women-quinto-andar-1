package br.com.report.report.controller;

import br.com.report.report.model.Log;

import br.com.report.report.service.LogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogServiceImp logServiceImp;


    @PostMapping("/log")
    public Log toSave(@RequestBody Log log){
        return logServiceImp.toSave(log);
    }

    @GetMapping("/log/{id}")
    public Optional<Log> findById(@PathVariable(value = "id")  Long id){
        return logServiceImp.findById(id);
    }

    @PutMapping("/log")
    public void changeStatus(@RequestBody Log log){
        logServiceImp.changeStatus(log);
    }

    @GetMapping("/log")
    public List<Log> findAll() {
        return logServiceImp.findAll();
    }

    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment) {
        return logServiceImp.findLogByEnvironment(environment);
    }

    @GetMapping("/log/{environment}/{orderBy}")
    public List<Log> findLogByEnvironmentAndOrderBy(@PathVariable(value = "environment") String environment, @PathVariable(value = "orderBy") String orderBy){
        return logServiceImp.findLogByEnvironmentAndOrderBy(environment,orderBy);
    }
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment, @PathVariable(value = "searchBy")String searchBy){
        return logServiceImp.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }

    @GetMapping("/log/{environment}/{orderBy}/{searchBy}")
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(@PathVariable(value = "environment") String environment,@PathVariable(value = "orderBy") String orderBy,@PathVariable(value="searchBy") String searchBy) {
        return logServiceImp.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
    }


}
