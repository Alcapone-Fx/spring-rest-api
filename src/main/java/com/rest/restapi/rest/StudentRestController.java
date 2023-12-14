package com.rest.restapi.rest;

import com.rest.restapi.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> students;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();

        students.add(new Student("Firulai", "De la barca"));
        students.add(new Student("Sacundino", "Segundo"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }


    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId >= students.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return students.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e) {
        StudentErrorResponse studentErrorResponseBody = new StudentErrorResponse();
        studentErrorResponseBody.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponseBody.setMessage(e.getMessage());
        studentErrorResponseBody.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception e) {
        StudentErrorResponse studentErrorResponseBody = new StudentErrorResponse();
        studentErrorResponseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponseBody.setMessage(e.getMessage());
        studentErrorResponseBody.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponseBody, HttpStatus.BAD_REQUEST
        );
    }
}
