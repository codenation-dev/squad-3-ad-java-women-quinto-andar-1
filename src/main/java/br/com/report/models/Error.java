package br.com.report.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "TB_ERRO")
public class Error implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogId")
    private long id;
/*
    @Column(name = "CollectedBy")
    private int collectedBy;
*/
    @Column(name="Level", nullable = false)
    @Size(min = 1, max = 20)
    private String level;

    @Column(name="Origin", nullable = false)
    @Size(min = 1, max = 50)
    private String origin;

    @Column(name="Description", nullable = false)
    @Size(min = 1, max = 128)
    private String description;

    @Column(name="Details", nullable = false)
    @Size(min = 1, max = 300)
    private String details;

    private Timestamp dataLogged;

    @Column(name="Status", nullable = false)
    @Size(min = 1, max = 10)
    private String status;
/*
    @Column(name = "LastModifiedBy")
    private int lastModifiedBy;
*/
    @Column(name="Environment", nullable = false)
    @Size(min = 1, max = 50)
    private String environment;

}