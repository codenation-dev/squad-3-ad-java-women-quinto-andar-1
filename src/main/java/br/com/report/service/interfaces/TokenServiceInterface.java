package br.com.report.service.interfaces;

import br.com.report.entity.Token;

public interface TokenServiceInterface {

    Token addToken(Token token);
    Token findById(long id);

}
