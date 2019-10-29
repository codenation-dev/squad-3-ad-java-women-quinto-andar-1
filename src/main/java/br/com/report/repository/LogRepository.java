package br.com.report.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.report.entity.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findLogByEnvironment(String environment);

    @Query(value = "SELECT * " +
            "FROM logs " +
            "WHERE environment= :environment " +
            "ORDER BY :orderBy",
            nativeQuery = true)
    List<Log> findLogByEnvironmentAndOrderBy(String environment, String orderBy);

    @Query(value = "SELECT * " +
            "FROM logs " +
            "WHERE environment = :environment" +
            "AND (level = :searchBy" +
            "OR origin = :searchBy" +
            "OR description = :searchBy )", nativeQuery = true)
    List<Log> findLogByEnvironmentAndSearchBy(String environment, String searchBy);

    @Query(value = "SELECT * " +
            "FROM logs " +
            "WHERE environment = :environment" +
            "AND (level = :searchBy" +
            "OR origin = :searchBy" +
            "OR description = :searchBy )" +
            "ORDER BY :orderBy", nativeQuery = true)
    List<Log> findLogByEnvironmentAndOrderByAndSearchBy(String environment, String orderBy, String searchBy);
}
