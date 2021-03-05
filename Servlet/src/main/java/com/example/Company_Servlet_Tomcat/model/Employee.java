package com.example.Company_Servlet_Tomcat.model;

import java.util.UUID;

public class Employee {
    private String name;
    private String department;
    private final String ID;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
        ID = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }
}

