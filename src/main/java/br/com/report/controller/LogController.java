package br.com.report.controller;

import br.com.report.entity.Log;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import br.com.report.service.impl.LogService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogService logServiceImpl;


    @ApiOperation(value = "Add a new log")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping("/log")
    public ResponseEntity<Log> addLog(@RequestBody Log log){
        try{
            return new ResponseEntity<>(logServiceImpl.addLog(log), HttpStatus.CREATED);
        }catch(Exception e){
            throw e;
        }
    }

    @ApiOperation(value = "Finds a log by its id")
    @GetMapping("/log/{id}")
    public Optional<Log> findById(@PathVariable(value = "id")  Long id){
        Optional<Log> log = logServiceImpl.findById(id);
        log.orElseThrow(()-> new NullPointerException("Value is not found"+ id));
        return log;
    }

    @ApiOperation(value = "Modify log delete / filed status")
    @PutMapping("/log")
    public void changeStatus(@RequestBody Log log){
        logServiceImpl.changeStatus(log);
    }

    @ApiOperation(value = "Return a list with all the logs")
    @GetMapping("/log")
    public List<Log> findAll() throws NotFoundException {
        if(logServiceImpl.findAll().isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logServiceImpl.findAll();
    }

    @ApiOperation(value = "Lists logs in chosen environment")
    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment) throws NotFoundException {
        if(logServiceImpl.findLogByEnvironment(environment).isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logServiceImpl.findLogByEnvironment(environment);
    }
    @ApiOperation(value = "Return logs by environment and ordered by the chosen parameter")
    @GetMapping("/log/{environment}/{orderBy}")
    public List<Log> findLogByEnvironmentAndOrderBy(@PathVariable(value = "environment") String environment, @PathVariable(value = "orderBy") String orderBy) throws NotFoundException {
        if(logServiceImpl.findLogByEnvironmentAndOrderBy(environment,orderBy).isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logServiceImpl.findLogByEnvironmentAndOrderBy(environment,orderBy);
    }
    @ApiOperation(value = "Return logs by environment and with inserted search term")
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment, @PathVariable(value = "searchBy")String searchBy) throws NotFoundException {
        if(logServiceImpl.findLogByEnvironmentAndSearchBy(environment, searchBy).isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logServiceImpl.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }
    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @GetMapping("/log/{environment}/{orderBy}/{searchBy}")
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(@PathVariable(value = "environment") String environment,@PathVariable(value = "orderBy") String orderBy,@PathVariable(value="searchBy") String searchBy) throws NotFoundException {
        if(logServiceImpl.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy).isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logServiceImpl.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
    }




}
