package com.rest.restapi.rest;

import com.rest.restapi.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Firulai", "De la barca"));
        students.add(new Student("Sacundino", "Segundo"));

        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Firulai", "De la barca"));
        students.add(new Student("Sacundino", "Segundo"));

        return students.get(studentId);
    }
}
