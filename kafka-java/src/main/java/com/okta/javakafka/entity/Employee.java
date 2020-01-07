package com.okta.javakafka.entity;

import java.util.List;

public class Employee {

	String id;
	String name;
	String designation;
	List<Department> department;
	

	public Employee(String id, String name, String designation, List<Department> department) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", department=" + department
				+ "]";
	}
   
}
