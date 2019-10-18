package br.com.report.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TB_Token")
public class Token implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TokenId")
    private Long id;

    @Column(name = "UseriId", nullable = false)
    private Long userId;

    @Column(name = "Token", nullable = false)
    private String token;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "ModificationDate", nullable = false)
    private LocalDate modificationDate;

    @Column(name = "CreationDate", nullable = false)
    private LocalDate creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
