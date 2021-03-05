package com.example.Company_Servlet_Tomcat.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {

    private EmployeeDB() {

    }

    private static final List<Employee> employees = new ArrayList<>();

    public static List<Employee> getEmployees() {
        return employees;
    }
}

