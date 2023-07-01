package com.example.employeemanagement.controller;

import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1/")
public class employeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // get employee by id rest api
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Employee ID: " + employeeId ));
        return ResponseEntity.ok(employee);
    }

    //update employee rest api
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Employee ID: " + employeeId ));

        employee.setTitle(employeeDetails.getTitle());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setContactNo(employeeDetails.getContactNo());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDob(employeeDetails.getDob());
        employee.setAddress(employeeDetails.getAddress());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity <Map<String, Boolean>> deleteEmployee(@PathVariable Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with Employee ID: " + employeeId));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put ("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
