package com.vetrix.JPA.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student){
        repository.save(student);
    }

    public List<Student> getStudents(){
        return repository.findOrder();
    }

    public Student getStudentByNumber(String number){
        List<Student> students = repository.findByNumber(number);
        if (students.size() == 0)
            throw new IllegalStateException("This student does not exist");
        return students.get(0);
    }

    public void updateStudent(String number,Student student){
        List<Student> students = repository.findByNumber(number);
        if (students.size() == 0)
            throw new IllegalStateException("This student does not exist");

        repository.findById(students.get(0).getId()).map(s -> {
            s.setLastname(student.getLastname());
            s.setFirstname(student.getFirstname());
            s.setNumber(student.getNumber());
            s.setDatenais(student.getDatenais());
            return repository.save(s);
        });
    }

    public void delete(String number){
        List<Student> students = repository.findByNumber(number);
        if (students.size() == 0)
            throw new IllegalStateException("This student does not exist");
        repository.deleteById(students.get(0).getId());
    }
}
