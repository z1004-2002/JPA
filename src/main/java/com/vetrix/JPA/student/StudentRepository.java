package com.vetrix.JPA.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("select s from Student s where s.number = ?1")
    public List<Student> findByNumber(String number);

    @Query("select s from Student s ORDER BY s.lastname, s.firstname")
    public List<Student> findOrder();
}
