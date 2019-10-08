package br.com.report.resources;

import br.com.report.models.User;
import br.com.report.repository.ErrorCenterRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResources {

    @Autowired
    private ErrorCenterRepository errorCenterRepository;

    @ApiOperation(value = "Return list os users")
    @GetMapping("/users")
    public List<User> userList(){
        return errorCenterRepository.findAll();
    }

    @ApiOperation(value = "Return one user")
    @GetMapping("/user/id")
    public User user(@PathVariable(value = "id") long id){
        return errorCenterRepository.findById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){

        return errorCenterRepository.save(user);
    }

    @DeleteMapping("/user")
    public User delUser(@RequestBody User user){
        errorCenterRepository.delete(user);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return errorCenterRepository.save(user);
    }

}
