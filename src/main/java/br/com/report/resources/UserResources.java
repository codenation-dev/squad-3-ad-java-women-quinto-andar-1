package br.com.report.resources;

import br.com.report.models.User;
import br.com.report.repository.CentralErrorRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResources {

    @Autowired
    private CentralErrorRepository centralErrorRepository;

    @ApiOperation(value = "Return list os users")
    @GetMapping("/users")
    public List<User> userList(){
        return centralErrorRepository.findAll();
    }

    @ApiOperation(value = "Return one user")
    @GetMapping("/user/id")
    public User user(@PathVariable(value = "id") long id){
        return centralErrorRepository.findById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){

        return centralErrorRepository.save(user);
    }

    @DeleteMapping("/user")
    public User delUser(@RequestBody User user){
        centralErrorRepository.delete(user);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return centralErrorRepository.save(user);
    }

}
