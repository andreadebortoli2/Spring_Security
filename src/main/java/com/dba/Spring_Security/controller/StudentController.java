package com.dba.Spring_Security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.dba.Spring_Security.model.Student;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "John", "Java"),
            new Student(2, "Jane", "Go")));

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("students")
    public void addStudent(@RequestBody Student student) {
        students.add(student);
    }

}
