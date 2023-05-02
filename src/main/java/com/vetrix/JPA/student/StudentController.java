package com.vetrix.JPA.student;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/student")
@Tag(name = "Student")
@CrossOrigin("*")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> findStudent(){
        return service.getStudents();
    }

    @GetMapping("/{number}")
    public Student getByNumber(@PathVariable String number){
        return service.getStudentByNumber(number);
    }

    @PutMapping("/update/{number}")
    public Student updateStudent(@PathVariable String number, @RequestBody Student student){
        service.updateStudent(number, student);
        return student;
    }
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        service.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @DeleteMapping("/{number}")
    public ResponseEntity<String> delete(@PathVariable String number){
        service.delete(number);
        return new ResponseEntity<String>("Success",HttpStatus.ACCEPTED);
    }
}
