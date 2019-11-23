package br.com.report.payload;

import javax.validation.constraints.*;

public class SignUpRequest {
    @NotBlank
    @Size(max = 20)
    private String login;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    public SignUpRequest(@NotBlank @Size(max = 20) String login, @NotBlank @Size(max = 100) @Email String email, @NotBlank @Size(max = 100) String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}