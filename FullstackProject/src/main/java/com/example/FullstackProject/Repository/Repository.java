package com.example.FullstackProject.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.FullstackProject.Student.Student;

public interface Repository extends JpaRepository<Student, Integer>{

}
