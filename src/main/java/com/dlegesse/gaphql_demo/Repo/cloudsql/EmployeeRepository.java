package com.dlegesse.gaphql_demo.Repo.cloudsql;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;



import com.dlegesse.gaphql_demo.Entity.cloudsql.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}