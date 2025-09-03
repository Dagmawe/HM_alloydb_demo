package com.dlegesse.gaphql_demo.Entity.cloudsql;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;


@Entity

@Table(name = "employees")
@Data
public class Employee {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String DOB;



    public Employee() {

    }

    public Employee(Integer id, String name, String DOB) {
        this.id = id;
        this.DOB = DOB;
        this.name = name;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
