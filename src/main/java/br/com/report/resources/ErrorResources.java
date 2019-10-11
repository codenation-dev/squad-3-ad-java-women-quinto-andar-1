package br.com.report.resources;

import br.com.report.models.Error;
import br.com.report.repository.ErrorRepository;
import br.com.report.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ErrorResources {

    @Autowired
    private ErrorRepository errorRepository;

    @PostMapping("/error")
    public Error error(@RequestBody Error error){
        return errorRepository.save(error);
    }

    @GetMapping("/errors")
    public List<Error> errorList(){
        return errorRepository.findAll();
    }

    @DeleteMapping("/error/{id}")
    public String delError(@PathVariable(value = "id") long id, @RequestBody String token){
        //if token is valido
        errorRepository.deleteById(id);
        return "Erro deletado com sucesso!";
    }


}
