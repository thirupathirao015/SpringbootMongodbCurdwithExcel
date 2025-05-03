package com.example.SpringbootMongodbCurdwithExcel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"eid",
	"name",
	"sal",
	"address",
	"designation"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Employee")
public class Employee {
	
	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("eid")
	private String eid;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("sal")
	private String sal;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("designation")
	private String designation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	
}
