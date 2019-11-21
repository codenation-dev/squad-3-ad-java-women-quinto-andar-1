package br.com.report.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.report.entity.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(value = "SELECT * " +
            "FROM tb_log " +
            "WHERE environment= :environment ;",
            nativeQuery = true)
    List<Log> findLogByEnvironment(@Param("environment")String environment);

    @Query(value = "SELECT * " +
            "FROM tb_log " +
            "WHERE environment= :environment " +
            "ORDER BY level ;",
            nativeQuery = true)
    List<Log> findLogByEnvironmentAndOrderByLevel(@Param("environment")String environment);

    @Query(value = "SELECT * " +
            "FROM tb_log " +
            "WHERE environment= :environment " +
            "ORDER BY event ;",
            nativeQuery = true)
    List<Log> findLogByEnvironmentAndOrderByEvent(@Param("environment")String environment);

    @Query(value = "SELECT * " +
            "FROM tb_log " +
            "WHERE environment =:environment " +
            "AND (level =:searchBy " +
            "OR origin =:searchBy " +
            "OR description like '%'||:searchBy||'%' );", nativeQuery = true)
    List<Log> findLogByEnvironmentAndSearchBy(@Param("environment") String environment,@Param("searchBy") String searchBy);

    @Query(value = "SELECT * " +
            "FROM tb_log " +
            "WHERE environment = :environment " +
            "AND (level = :searchBy " +
            "OR origin = :searchBy " +
            "OR description like '%'||:searchBy||'%' ) " +
            "ORDER BY level ;", nativeQuery = true)
    List<Log> findLogByEnvironmentAndSearchByAndOrderByLevel(@Param("environment")String environment, @Param("searchBy")String searchBy);

    @Query(value = "SELECT * " +
            "FROM tb_log " +
            "WHERE environment = :environment " +
            "AND (level = :searchBy " +
            "OR origin = :searchBy " +
            "OR description like '%'||:searchBy||'%' ) " +
            "ORDER BY event ;", nativeQuery = true)
    List<Log> findLogByEnvironmentAndSearchByAndOrderByEvent(@Param("environment")String environment, @Param("searchBy")String searchBy);
}
