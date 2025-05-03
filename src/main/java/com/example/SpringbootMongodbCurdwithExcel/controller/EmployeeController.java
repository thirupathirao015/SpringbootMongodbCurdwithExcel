package com.example.SpringbootMongodbCurdwithExcel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringbootMongodbCurdwithExcel.model.Employee;
import com.example.SpringbootMongodbCurdwithExcel.serviceImpl.EmployeeServiceImpl;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping("/addEmployee")
	public String saveBook(@RequestBody Employee employee) {
		employeeServiceImpl.saveEmployee(employee);
		return "Added Successfully";
	}

	// http://localhost:7777/getEmployee/1009
	@GetMapping("/getEmployee/{eid}")
	public List<Employee> getEmployee(@PathVariable String eid) {
		return employeeServiceImpl.getEmployeeByEid(eid);
	}

	@GetMapping("/getEmployee/{eid}/{name}")
	public List<Employee> getemployeeList(@PathVariable String eid, @PathVariable String name) {
		return employeeServiceImpl.getEmployeeByEidAndName(eid, name);
	}

	@GetMapping("/getEmployees/byname/{name}")
	public List<Employee> getemployeeByName(@PathVariable String name) {
		return employeeServiceImpl.getEmployeeByName(name);
	}

}
