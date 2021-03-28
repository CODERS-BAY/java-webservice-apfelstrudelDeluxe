package com.example.Company_Servlet_Tomcat.servlets;

import com.example.Company_Servlet_Tomcat.model.Employee;
import com.example.Company_Servlet_Tomcat.model.EmployeeDB;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        EmployeeDB.getEmployees().add(new Employee("Karl Huber", "Verkauf"));
        EmployeeDB.getEmployees().add(new Employee("Sepp Huber", "Lager"));
        EmployeeDB.getEmployees().add(new Employee("Edeltraud Walchshofer", "Büro"));
        EmployeeDB.getEmployees().add(new Employee("Gitti Jazz", "Sängerin"));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("action") == null) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append("provide action parameter");
        }

        String action = request.getParameter("action");

        switch (action) {
            case "all":
                getAll(response);
                break;
            case "get":
                getEmployeeByName(request, response);
                break;
            default:
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().append("wrong parameter ").append(action);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        private void getAll(HttpServletResponse response) throws IOException {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append(new Gson().toJson(EmployeeDB.getEmployees()));
        }

        private void getEmployeeByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            Employee foundEmployee = null;
            for (Employee e : EmployeeDB.getEmployees()) {
                if (e.getName().equals(name)) {
                    foundEmployee = e;
                    break;
                }
            }

            if (foundEmployee == null) {
                response.getWriter().append(new Gson().toJson("Person not found"));
            } else {
                response.getWriter().append(new Gson().toJson(foundEmployee));
            }
        }

    }
}
