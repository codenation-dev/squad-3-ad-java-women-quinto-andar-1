package br.com.report.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_log")
public class Log implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 20)
    private String level;

    @Column(nullable = false)
    @Size(max = 50)
    private String origin;

    @Column(nullable = false)
    @Size(max = 128)
    private String description;

    @Column(nullable = false)
    @Size(max = 300)
    private String details;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant dataLogged;

    @Column(name="status", nullable = false)
    @Size(max = 10)
    private String status;

    @Column(nullable = false)
    @Size(max = 50)
    private String environment;

    @OneToOne
    private User user;

    public Log(@Size(max = 20) String level, @Size(max = 50) String origin, @Size(max = 128) String description, @Size(max = 300) String details, @Size(max = 10) String status, @Size(max = 50) String environment, User user) {
        this.level = level;
        this.origin = origin;
        this.description = description;
        this.details = details;
        this.status = status;
        this.environment = environment;
        this.user = user;
    }
}