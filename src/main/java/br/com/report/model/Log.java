package br.com.report.model;

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

    @Column(name = "collected_by")
    private int collectedBy;

    @Column(name="level", nullable = false)
    @Size(min = 1, max = 20)
    private String level;

    @Column(name="origin", nullable = false)
    @Size(min = 1, max = 50)
    private String origin;

    @Column(name="description", nullable = false)
    @Size(min = 1, max = 128)
    private String description;

    @Column(name="details", nullable = false)
    @Size(min = 1, max = 300)
    private String details;

    private Timestamp dataLogged;

    @Column(name="status", nullable = false)
    @Size(min = 1, max = 10)
    private String status;

    @Column(name = "last_modified_by")
    private int lastModifiedBy;

    @Column(name="environment", nullable = false)
    @Size(min = 1, max = 50)
    private String environment;

    @OneToOne
    private Token token;




}