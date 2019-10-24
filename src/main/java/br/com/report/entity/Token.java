package br.com.report.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_token")
public class Token implements Serializable {

    private static final long serialVersionId = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "modification_date", nullable = false)
    private LocalDate modificationDate;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany
    private List<Log> logs;


}
