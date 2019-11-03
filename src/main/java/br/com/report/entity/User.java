package br.com.report.entity;

import br.com.report.entity.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "tb_user")
public class User extends DateAudit implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 20)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @Column(nullable = false)
    @Size(max = 100)
    private String email;

    private Boolean active = true;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Token token;

    public User(){

    }

    public User(String login, String email, String password) {
        this.password = password;
        this.email = email;
        this.login = login;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
/*
    private String dateNow(){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }*/
}
