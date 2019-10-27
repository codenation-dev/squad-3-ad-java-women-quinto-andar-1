package br.com.report.controller;

import br.com.report.entity.User;
import br.com.report.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable(value = "id") long id){
        return userService.findById(id);
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PutMapping("/user")
    public void changeStatus(@RequestBody User user){
         userService.changeStatus(user);
    }

}
