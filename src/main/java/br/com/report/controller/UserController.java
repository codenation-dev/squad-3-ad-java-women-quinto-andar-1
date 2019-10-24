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











    @PutMapping("/user/{id}")
    public User changeStatus(@PathVariable(value = "id") Long id, @RequestBody User user){
        user.setActive(!user.getActive());
        //userService.changeStatus(id);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return userService.addUser(user);
    }

}
