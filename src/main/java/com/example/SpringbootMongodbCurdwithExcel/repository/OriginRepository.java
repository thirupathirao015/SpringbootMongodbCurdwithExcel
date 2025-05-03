package com.example.SpringbootMongodbCurdwithExcel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringbootMongodbCurdwithExcel.model.Employee;
import java.util.List;


@Repository
public interface OriginRepository extends MongoRepository<Employee, String> {

	List<Employee> findByEid(String eid);
	
	List<Employee> findByEidAndName(String eid,String name);
	
	List<Employee> findByName(String name);
	
	void deleteByEid(String eid);
	
}
