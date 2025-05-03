package com.example.SpringbootMongodbCurdwithExcel.serviceInterf;

import java.util.List;

import com.example.SpringbootMongodbCurdwithExcel.model.Employee;

public interface IEmployee {
	
	void saveEmployee(Employee employee);
	
	List<Employee> getEmployeeByEid(String eid);
	
	List<Employee> getEmployeeByEidAndName(String eid,String name);
	
	void deleteEmployeeByEid(String eid);
	
	List<Employee> getEmployeeByName(String name);
	

}
