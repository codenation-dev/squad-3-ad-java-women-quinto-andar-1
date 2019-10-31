package br.com.report.controller;

import br.com.report.entity.Token;
import br.com.report.service.impl.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private TokenService tokenServiceImpl;

    @PostMapping("/token")
    @ApiOperation(value = "Adds Tokens to the database.")
    public Token addToken(@RequestBody Token token){
        return tokenServiceImpl.addToken(token);
    }

    @GetMapping("/token/{id}")
    @ApiOperation(value = "Finds a Token through its id.")
    public Token findById(@PathVariable(value = "id") long id){
        return tokenServiceImpl.findById(id);
    }

}
