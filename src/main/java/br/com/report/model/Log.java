package br.com.report.model;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "logs")
public class Log implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCollectedBy() {
        return collectedBy;
    }

    public void setCollectedBy(int collectedBy) {
        this.collectedBy = collectedBy;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getDataLogged() {
        return dataLogged;
    }

    public void setDataLogged(Timestamp dataLogged) {
        this.dataLogged = dataLogged;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

}