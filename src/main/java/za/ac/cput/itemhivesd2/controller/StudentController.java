package za.ac.cput.itemhivesd2.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.itemhivesd2.domain.Student;
import za.ac.cput.itemhivesd2.service.StudentService;

import java.util.List;

/*
StudentController.java
Student controller class
Author: Mihle Nosilela (240865537)
Date: June 2026
*/

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService serv;

    public StudentController(StudentService serv) {
        this.serv = serv;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return serv.create(student);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return serv.update(student);
    }

    @GetMapping("/{id}")
    public Student read(@PathVariable String id) {
        return serv.read(id);
    }

    @GetMapping
    public List<Student> getAll() {
        return serv.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        serv.delete(id);
    }
}