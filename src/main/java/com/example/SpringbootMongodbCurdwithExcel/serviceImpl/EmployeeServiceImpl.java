package com.example.SpringbootMongodbCurdwithExcel.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringbootMongodbCurdwithExcel.model.Employee;
import com.example.SpringbootMongodbCurdwithExcel.repository.OriginRepository;
import com.example.SpringbootMongodbCurdwithExcel.serviceInterf.IEmployee;

@Service
public class EmployeeServiceImpl implements IEmployee {
	
	@Autowired
	private OriginRepository originRepository;

	@Override
	public void saveEmployee(Employee employee) {
		originRepository.save(employee);	
	}

	@Override
	public List<Employee> getEmployeeByEid(String eid) {		
		return originRepository.findByEid(eid);		
	}


	@Override
	public void deleteEmployeeByEid(String eid) {
		originRepository.deleteByEid(eid);
	}


	@Override
	public List<Employee> getEmployeeByEidAndName(String eid, String name) {
		// TODO Auto-generated method stub
		return originRepository.findByEidAndName(eid, name);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return originRepository.findByName(name);
	}

}
