package br.com.report.resources;

import br.com.report.model.Log;
import br.com.report.repository.LogRepository;
import br.com.report.repository.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LogResources {

    @Autowired
    private LogRepository logRepository;

    @PostMapping("/log")
    public Log log(@RequestBody Log log){
        return logRepository.save(log);
    }

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
    }


}
