package br.com.report.report.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
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
    private Boolean active = true;

    @NotBlank
    @Column(name="last_activity", nullable = false)
    @Size(min = 1, max = 100)
    private String lastActivity;

    @OneToOne(mappedBy = "user")
    private Token token;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
