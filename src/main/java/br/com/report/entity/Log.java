package br.com.report.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

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

    private Timestamp dataLogged;

    @Column(name="status", nullable = false)
    @Size(max = 10)
    private String status;

    @Column(nullable = false)
    @Size(max = 50)
    private String environment;

    @OneToOne
    private User tokenCollected;

}