package br.com.report.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name="login", nullable = false)
    @Size(min = 1, max = 20)
    private String login;

    @NotBlank
    @Column(name="password", nullable = false)
    @Size(min = 1, max = 100)
    private String password;

    @NotBlank
    @Column(name="email", nullable = false)
    @Size(min = 1, max = 100)
    private String email;

    @NotBlank
    @Column(name="creation_date", nullable = false)
    @Size(min = 1, max = 100)
    private String creationDate;

    @NotBlank
    @Column(name="active", nullable = false)
    @Size(min = 1, max = 100)
    private String active;

    @NotBlank
    @Column(name="last_activity", nullable = false)
    @Size(min = 1, max = 100)
    private String lastActivity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }
}
