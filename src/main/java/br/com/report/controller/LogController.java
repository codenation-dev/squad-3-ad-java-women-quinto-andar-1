package br.com.report.controller;

import br.com.report.entity.Log;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import br.com.report.service.impl.LogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Log addLog(@RequestBody Log log){
        return logServiceImpl.addLog(log);
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

    @ApiOperation(value = "Lists logs in chosen environment")
    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment) {
        return logServiceImpl.findLogByEnvironment(environment);
    }
    @ApiOperation(value = "Return logs by environment and ordered by the chosen parameter")
    @GetMapping("/log/{environment}/{orderBy}")
    public List<Log> findLogByEnvironmentAndOrderBy(@PathVariable(value = "environment") String environment, @PathVariable(value = "orderBy") String orderBy){
        return logServiceImpl.findLogByEnvironmentAndOrderBy(environment,orderBy);
    }
    @ApiOperation(value = "Return logs by environment and with inserted search term")
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment, @PathVariable(value = "searchBy")String searchBy){
        return logServiceImpl.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }
    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @GetMapping("/log/{environment}/{orderBy}/{searchBy}")
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(@PathVariable(value = "environment") String environment,@PathVariable(value = "orderBy") String orderBy,@PathVariable(value="searchBy") String searchBy) {
        return logServiceImpl.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
    }




}
