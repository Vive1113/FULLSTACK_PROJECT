package com.example.FullstackProject.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FullstackProject.ExceptionHandling.ResourceNotFound;
import com.example.FullstackProject.Repository.Repository;
import com.example.FullstackProject.Student.Student;

/* @RestController annotation is applied to a class to mark it as a request handler. 
 * This annotation itself annotated with @Controller and @ResponseBody. 
 * @Controller is used for mapping
 * @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON 
 * and passed back into the HttpResponse object. */

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/studentDetails/v1/")
public class Controller {
	@Autowired
	private Repository repository;
	
	// get all Students
	@GetMapping("/GetStudents")
	public List<Student> getAllStudents(){
		return repository.findAll();
	}		
	
	// create Student rest api
	@PostMapping("/CreateStudents")
	public Student createStudent(@RequestBody Student student) {
		return repository.save(student);
	}
	
	// get Student by id rest api
	@GetMapping("/GetStudent/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
		Student student = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Student not exist with id :" + id));
		return ResponseEntity.ok(student);
	}
	
	// update Student rest api
	
	@PutMapping("/UpdateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails){
		Student student = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Student not exist with id :" + id));
		
		student.setStudentName(studentDetails.getStudentName());
		student.setStandard(studentDetails.getStandard());
		student.setContactNo(studentDetails.getContactNo());
		
		Student updatedStudent = repository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	// delete Student rest api
	@DeleteMapping("/DeleteStudent/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer id){
		Student student = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Student not exist with id :" + id));
		
		repository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
