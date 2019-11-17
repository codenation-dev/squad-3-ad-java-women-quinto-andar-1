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
    private LogService logService;


    @ApiOperation(value = "Add a new log")
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "Bad Request")
//    })
    @PostMapping("/log")
    public ResponseEntity<Log> addLog(@RequestBody Log log){
        try{
            return new ResponseEntity<>(logService.addLog(log), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(new br.com.report.payload.ApiResponse(false, "Error trying to register a log"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Finds a log by its id")
    @GetMapping("/log/{id}")
    public Optional<Log> findById(@PathVariable(value = "id") long id) throws NotFoundException {
        Optional<Log> log = logService.findById(id);
        log.orElseThrow(()-> new NotFoundException("Not found log with id: " + id));
        return log;
    }

    @ApiOperation(value = "Modify log delete / filed status")
    @PutMapping("/log/id")
    public void changeStatus(@RequestBody Log log, @PathVariable("id") Long id) throws NotFoundException {
        Optional<Log> findLog = logService.findById(id);
        findLog.orElseThrow(()-> new NotFoundException("Not found user with id: " + id));
        logService.changeStatus(log);
    }

    @ApiOperation(value = "Return a list with all the logs")
    @GetMapping("/log")
    public List<Log> findAll() throws NotFoundException {
        List<Log> logs = logService.findAll();
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Lists logs in chosen environment")
    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment)
            throws NotFoundException {
        List<Log> logs = logService.findLogByEnvironment(environment);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and ordered by level")
    @GetMapping("/log/envOrdLev/{environment}")
    public List<Log> findLogByEnvironmentAndOrderByLevel(@PathVariable(value = "environment") String environment)
            throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndOrderByLevel(environment);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and ordered by event")
    @GetMapping("/log/envOrdEve/{environment}")
    public List<Log> findLogByEnvironmentAndOrderByEvent(@PathVariable(value = "environment") String environment)
            throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndOrderByEvent(environment);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and with inserted search term")
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment,
                                                     @PathVariable(value = "searchBy")String searchBy)
            throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchBy(environment, searchBy);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @GetMapping("/log/envOrdLev/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchByAndOrderByLevel(@PathVariable(value = "environment") String environment,
                                                                    @PathVariable(value="searchBy") String searchBy) throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchByAndOrderByLevel(environment, searchBy);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @GetMapping("/log/envOrdEve/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchByAndOrderByEvent(@PathVariable(value = "environment") String environment,
                                                                    @PathVariable(value="searchBy") String searchBy) throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchByAndOrderByEvent(environment, searchBy);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }
/*
    @ApiOperation(value = "Lists logs in chosen environment")
    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment)
            throws NotFoundException {
        List<Log> logs = logService.findLogByEnvironment(environment);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and ordered by the chosen parameter")
    @GetMapping("/log/{environment}/{orderBy}")
    public List<Log> findLogByEnvironmentAndOrderBy(@PathVariable(value = "environment") String environment,
                                                    @PathVariable(value = "orderBy") String orderBy)
                                                    throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndOrderBy(environment,orderBy);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and with inserted search term")
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment,
                                                     @PathVariable(value = "searchBy")String searchBy)
                                                     throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchBy(environment, searchBy);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @GetMapping("/log/{environment}/{orderBy}/{searchBy}")
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(@PathVariable(value = "environment") String environment,
                                                               @PathVariable(value = "orderBy") String orderBy,
                                                               @PathVariable(value="searchBy") String searchBy)
                                                               throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
        if(logs.isEmpty()){
            throw new NotFoundException("The list is empty");
        }
        return logs;
    }

 */
}
