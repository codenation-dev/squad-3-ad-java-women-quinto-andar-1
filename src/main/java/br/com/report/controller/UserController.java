package br.com.report.controller;

import br.com.report.entity.User;
import br.com.report.service.UserServiceServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceServiceImpl userServiceImplImp;

    @ApiOperation(value = "Return list os users")
    @GetMapping("/users")
    public List<User> userList(){
        return userServiceImplImp.findAll();
    }

    @ApiOperation(value = "Return one user")
    @GetMapping("/user/{id}")
    public Optional<User> user(@PathVariable(value = "id") long id){
        return userServiceImplImp.findById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userServiceImplImp.toSave(user);
    }

    @PutMapping("/user/{id}")
    public User changeStatus(@PathVariable(value = "id") Long id, @RequestBody User user){
        user.setActive(!user.getActive());
        //userService.changeStatus(id);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return userServiceImplImp.toSave(user);
    }

}
