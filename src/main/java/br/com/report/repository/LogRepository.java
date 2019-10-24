package br.com.report.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.report.entity.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

//    @Query
//    List<Log> findLogByEnvironment(String environment);
//
//    @Query
//    List<Log> findLogByEnvironmentAndOrderBy(String environment, String orderBy);

//    @Query
//    List<Log> findLogByEnvironmentAndSearchBy(String environment, String searchBy);

//    @Query
//    List<Log> findLogByEnvironmentAndOrderByAndSearchBy(String environment, String orderBy, String searchBy);
}
