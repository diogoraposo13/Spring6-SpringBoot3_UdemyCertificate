package com.diogoraposo.springboot.controller;

import com.diogoraposo.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(4,"Diogo","Raposo");
        return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok().header("custom-header").body(student)
    }

    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Diogo","Raposo"));
        students.add(new Student(2,"Cristiano","Ronaldo"));
        students.add(new Student(3,"Antonio","Silva"));
        students.add(new Student(4,"João","Neves"));
        return new ResponseEntity<>(students,HttpStatus.OK);
    }


    //http://localhost:8080/students/1/diogo/raposo
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student = new Student(studentId,firstName, lastName);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }


    //http://localhost:8080/students/query?id=1&firstName=Diogo&lastName=Raposo
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName,@RequestParam String lastName){
        Student student = new Student(id,firstName,lastName);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    //http://localhost:8080/students/create
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    //http://localhost:8080/students/3/update

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    //http://localhost:8080/students/3/delete

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK);
    }

}
