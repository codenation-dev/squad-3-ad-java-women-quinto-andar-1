package br.com.report.entity.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"creationDate", "lastActivity"},
        allowGetters = true
)
public class DateAudit implements Serializable {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastActivity;

}
