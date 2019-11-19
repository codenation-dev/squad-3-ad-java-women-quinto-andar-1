package br.com.report.entity;

import lombok.Getter;
import lombok.Setter;
import org.h2.api.DatabaseEventListener;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
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

    @CreationTimestamp
    @Column(updatable = false)
    private Date dataLogged;

    @Column(name="status", nullable = false)
    @Size(max = 10)
    private String status;

    @Column(nullable = false)
    @Size(max = 50)
    private String environment;

    @Column(name = "event",nullable = false)
    private int event;

    @OneToOne
    private User user;

    public Log(){

    }
    public Log(@Size(max = 20) String level, @Size(max = 50) String origin, @Size(max = 128) String description, @Size(max = 300) String details, @Size(max = 10) String status, @Size(max = 50) String environment, int event, User user) {
        this.level = level;
        this.origin = origin;
        this.description = description;
        this.details = details;
        this.status = status;
        this.environment = environment;
        this.event = event;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
}