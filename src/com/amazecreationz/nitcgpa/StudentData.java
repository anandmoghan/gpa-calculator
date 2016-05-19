//$Id$
package com.amazecreationz.nitcgpa;

public class StudentData {
	private String name, rollNo, branch;
	
	StudentData(String name, String rollNo, String branch){
		this.name = name;
		this.rollNo = rollNo;
		this.branch = branch;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getRollNo() {
		return this.rollNo;
	}
	
	public String getBranch() {
		return this.branch;
	}
}
