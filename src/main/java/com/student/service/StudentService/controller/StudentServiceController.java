package com.student.service.StudentService.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;


@RestController
@RequestMapping(value="/student-service")
public class StudentServiceController {
	private static Map<String, List<Student>> schooDB = new HashMap<String, List<Student>>();
    static {
        schooDB = new HashMap<String, List<Student>>();
 
        List<Student> lst = new ArrayList<Student>();
        Student std = new Student("Sajal", "Class IV");
        lst.add(std);
        std = new Student("Lokesh", "Class V");
        lst.add(std);
 
        schooDB.put("abcschool", lst);
 
        lst = new ArrayList<Student>();
        std = new Student("Kajal", "Class III");
        lst.add(std);
        std = new Student("Sukesh", "Class VI");
        lst.add(std);
 
        schooDB.put("xyzschool", lst);
 
    }
    
    @GetMapping(value="/getStudentDetailsForSchool/{schoolName}")
    public ResponseEntity<List<Student>> getStudents( @PathVariable("schoolName") String schoolName, @QueryParam("address") String address){
    	System.out.println("Getting Student details for " + schoolName);
    	System.out.println("address as query param : "+address);
        List<Student> studentList = schooDB.get(schoolName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return  new ResponseEntity<List<Student>>(studentList,headers,HttpStatus.OK); 
    }
    
    @PostMapping(value="/addStudentForSchool/{schoolName}")
    public ResponseEntity<Void> addStudents(@RequestBody Student student,@PathVariable("schoolName") String schoolName){
    	System.out.println("Adding Student for " + schoolName);
    	List<Student> studentList = schooDB.get(schoolName);
    	studentList.add(student);
    	schooDB.put(schoolName,studentList);
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    	return  new ResponseEntity<Void>(headers,HttpStatus.OK);
    }

}
