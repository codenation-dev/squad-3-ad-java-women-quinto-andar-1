package br.com.report.controller;

import br.com.report.entity.Log;

import br.com.report.entity.User;
import br.com.report.payload.LogRequest;
import br.com.report.payload.Response;
import io.swagger.annotations.ApiOperation;
import br.com.report.service.impl.LogService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered log", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PostMapping("/logs")
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the log", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/id/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") long id) {
        Optional<Log> log = logService.findById(id);
        if (log.isPresent())
            return new ResponseEntity<Log>(log.get(), HttpStatus.OK);

        return new ResponseEntity<>(
                new Response(false, "Not found log with id: " + id),
                HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Modify log delete / filed status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the log who was modified", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 404, message = "Log not found", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PutMapping("/logs/status/{id}")
    public ResponseEntity<?> changeStatus(@RequestBody Log log, @PathVariable("id") Long id) {
        Optional<Log> findLog = logService.findById(id);
        if(findLog.isPresent()){
            logService.changeStatus(log);
            return new ResponseEntity<Log>(findLog.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Response(false, "Not found log with id: " + id),
                HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Return a list with all the logs")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs")
    public List<Log> findAll() throws NotFoundException {
        List<Log> logs = logService.findAll();
        return logs;
    }

    @ApiOperation(value = "Lists logs in chosen environment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment)
            throws NotFoundException {
        List<Log> logs = logService.findLogByEnvironment(environment);
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and ordered by level")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/envOrdLev/{environment}")
    public List<Log> findLogByEnvironmentAndOrderByLevel(@PathVariable(value = "environment") String environment)
            throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndOrderByLevel(environment);
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and ordered by event")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/envOrdEve/{environment}")
    public List<Log> findLogByEnvironmentAndOrderByEvent(@PathVariable(value = "environment") String environment)
            throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndOrderByEvent(environment);
        return logs;
    }

    @ApiOperation(value = "Return logs by environment and with inserted search term")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment,
                                                     @PathVariable(value = "searchBy")String searchBy)
            throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchBy(environment, searchBy);
        return logs;
    }

    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/envOrdLev/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchByAndOrderByLevel(@PathVariable(value = "environment") String environment,
                                                                    @PathVariable(value="searchBy") String searchBy) throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchByAndOrderByLevel(environment, searchBy);
        return logs;
    }

    @ApiOperation(value = "Return logs by environment, with inserted search term and ordered by chosen parameter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list log", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/logs/envOrdEve/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchByAndOrderByEvent(@PathVariable(value = "environment") String environment,
                                                                    @PathVariable(value="searchBy") String searchBy) throws NotFoundException{
        List<Log> logs = logService.findLogByEnvironmentAndSearchByAndOrderByEvent(environment, searchBy);
        return logs;
    }
}
