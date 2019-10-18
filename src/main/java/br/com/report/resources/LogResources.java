package br.com.report.resources;

import br.com.report.model.Log;
import br.com.report.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogResources {

    @Autowired
    private LogRepository logRepository;

    @PostMapping("/error")
    public Log error(@RequestBody Log log){
        return logRepository.save(log);
    }

    @GetMapping("/errors")
    public List<Log> errorList(){
        return logRepository.findAll();
    }

    @DeleteMapping("/error/{id}")
    public String delError(@PathVariable(value = "id") long id, @RequestBody String token){
        //if token is valido
        logRepository.deleteById(id);
        return "Erro deletado com sucesso!";
    }


}
