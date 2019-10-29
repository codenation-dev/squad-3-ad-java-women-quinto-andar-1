package br.com.report.service.impl;

import br.com.report.entity.Token;
import br.com.report.repository.TokenRepository;
import br.com.report.service.interfaces.TokenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenService implements TokenServiceInterface {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token addToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Token findById(long id) {
        return tokenRepository.findById(id);
    }
}
