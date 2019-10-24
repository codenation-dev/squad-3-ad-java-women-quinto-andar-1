package br.com.report.controller;

import br.com.report.entity.Token;
import br.com.report.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    @PostMapping("/token")
    public Token addToken(@RequestBody Token token){
        return tokenServiceImpl.addToken(token);
    }

    @GetMapping("/token/{id}")
    public Token findById(@PathVariable(value = "id") long id){
        return tokenServiceImpl.findById(id);
    }

}
