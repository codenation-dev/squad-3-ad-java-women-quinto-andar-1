package br.com.report.controller;

import br.com.report.model.Log;
import br.com.report.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogService logService;


    @PostMapping("/log")
    public Log toSave(@RequestBody Log log){
        return logService.toSave(log);
    }

    @GetMapping("/log/{id}")
    public Optional<Log> findById(@PathVariable(value = "id")  Long id){
        return logService.findById(id);
    }

    @PutMapping("/log")
    public void changeStatus(@RequestBody Log log){
         logService.changeStatus(log);
    }

    @GetMapping("/log")
    public List<Log> findAll() {
        return logService.findAll();
    }

    @GetMapping("/log/{environment}")
    public List<Log> findLogByEnvironment(@PathVariable(value = "environment") String environment) {
        return logService.findLogByEnvironment(environment);
    }

    @GetMapping("/log/{environment}/{orderBy}")
    public List<Log> findLogByEnvironmentAndOrderBy(@PathVariable(value = "environment") String environment, @PathVariable(value = "orderBy") String orderBy){
        return logService.findLogByEnvironmentAndOrderBy(environment,orderBy);
    }
    @GetMapping("/log/{environment}/{searchBy}")
    public List<Log> findLogByEnvironmentAndSearchBy(@PathVariable(value = "environment")String environment, @PathVariable(value = "searchBy")String searchBy){
        return logService.findLogByEnvironmentAndSearchBy(environment, searchBy);
    }

    @GetMapping("/log/{environment}/{orderBy}/{searchBy}")
    public List<Log> findLogByEnvironmentAndOrderByAndSearchBy(@PathVariable(value = "environment") String environment,@PathVariable(value = "orderBy") String orderBy,@PathVariable(value="searchBy") String searchBy) {
        return logService.findLogByEnvironmentAndOrderByAndSearchBy(environment, orderBy, searchBy);
    }
        /*
    @GetMapping("/log")
    public List<Log> logist(){
        return logRepository.findAll();
    }

    @DeleteMapping("/log/{id}")
    public String delError(@PathVariable(value = "id") long id, @RequestBody String token){
        logRepository.deleteById(id);
        return "Log deletado com sucesso!";
    }
    @PutMapping("/log/{id}")
    public Log changeStatus(@PathVariable(value = "id") long id, @RequestBody String status){

        return this.changeStatus(id, status);
    }
    @GetMapping
    @ResponseBody
    public Page<Log> listar(LogFilter filter, @PageableDefault(page = 0, size = 10) Pageable paginacao) {

        if(filter.getLevel() != null) {
            return this.logRepository.findLogByEnvironmentAndLevelContaining(filter.getEnvironment(),
                    filter.getLevel(), paginacao);
        }

        if(filter.getTitle() != null) {
            return this.logRepository.findLogByEnvironmentAndTitleContaining(filter.getEnvironment(),
                    filter.getTitle(), paginacao);
        }

        if(filter.getSource() != null) {
            return this.logRepository.findLogByEnvironmentAndSourceContaining(filter.getEnvironment(),
                    filter.getSource(), paginacao);
        }

        if(filter.getEnvironment() != null) {
            return this.logRepository.findLogByEnvironment(filter.getEnvironment(), paginacao);
        }

        return this.logRepository.findAll(paginacao);
    }*/


}
