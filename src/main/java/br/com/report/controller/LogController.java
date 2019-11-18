package br.com.report.controller;

import br.com.report.entity.Log;

import br.com.report.entity.User;
import br.com.report.payload.LogRequest;
import br.com.report.payload.Response;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private UserController userController;

    @ApiOperation(value = "Add a new log")
    @PostMapping("/log")
    public ResponseEntity<Log> addLog(@RequestBody LogRequest logRequest){
        try{
            User user = userController.findByToken(logRequest.getUserToken());
            Log log = new Log(logRequest.getLevel(), logRequest.getOrigin(), logRequest.getDescription(), logRequest.getDetails(), logRequest.getStatus(), logRequest.getEnvironment(), logRequest.getEvent(), user);
            return new ResponseEntity<>(logService.addLog(log), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(new Response(false, "Error trying to register a log"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Finds a log by its id")
    @GetMapping("/log/id/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") long id) throws NotFoundException {
        Optional<Log> log = logService.findById(id);
        if (log.isPresent())
            return new ResponseEntity<Log>(log.get(), HttpStatus.OK);

        return new ResponseEntity(
                new Response(false, "Not found log with id: " + id),
                HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Modify log delete / filed status")
    @PutMapping("/log/status/{id}")
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
}
