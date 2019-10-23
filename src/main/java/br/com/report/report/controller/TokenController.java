package br.com.report.report.controller;

import br.com.report.report.model.Token;
import br.com.report.report.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("/tokens")
    public List<Token> tokenList() { return tokenRepository.findAll(); }

    @GetMapping("/token/{id}")
    public Token getToken(@PathVariable(value = "id") long id){
        return tokenRepository.findById(id);
    }

    @PatchMapping("/token/{id}")
    public Token deactivateToken(@PathVariable(value = "id") long id, @RequestBody Token token) {
        return tokenRepository.save(token);
    }

    @PostMapping("/token")
    public Token createToken(@RequestBody Token token){
        return tokenRepository.save(token);
    }

}
