package br.com.report.dto;

import br.com.report.entity.User;
import br.com.report.payload.JwtAuthenticationResponse;

public class ResponseDTO {

    public User user;
    public JwtAuthenticationResponse jwtAuthenticationResponse;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JwtAuthenticationResponse getJwtAuthenticationResponse() {
        return jwtAuthenticationResponse;
    }

    public void setJwtAuthenticationResponse(JwtAuthenticationResponse jwtAuthenticationResponse) {
        this.jwtAuthenticationResponse = jwtAuthenticationResponse;
    }
}
