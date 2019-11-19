package br.com.report.payload;

import br.com.report.entity.User;

public class LoginResponse {
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
