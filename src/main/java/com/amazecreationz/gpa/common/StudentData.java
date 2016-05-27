//$Id$
package com.amazecreationz.gpa.common;

import java.util.HashMap;

public class StudentData {
	private String name, rollNo, branch;
	private int passCredits, failedCredits;
	private HashMap<String, Float> sgpaMap;
	private HashMap<String, Integer> creditsMap;
	
	public StudentData(String name, String rollNo, String branch){
		this.name = name;
		this.rollNo = rollNo;
		this.branch = branch;
		this.passCredits = 0;
		this.failedCredits = 0;
		this.sgpaMap = new HashMap<String, Float>();
		this.creditsMap = new HashMap<String, Integer>();
	}
	
	public void setPassCredits(int passCredits) {
		this.passCredits += passCredits;
	}
	
	public void setFailedCredits(int failedCredits) {
		this.failedCredits += failedCredits;
	}
	
	public void setSGPA(String semester, Float sgpa) {
		sgpaMap.put(semester, sgpa);
	}
	
	public void setCredits(String semester, Integer credits) {
		creditsMap.put(semester, credits);
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
	
	public int getPassCredits() {
		return this.passCredits;
	}
	
	public int getFailedCredits() {
		return this.failedCredits;
	}
	
	public float getSGPA(String semester) {
		return sgpaMap.get(semester);
	}
	
	public int getCredits(String semester) {
		return creditsMap.get(semester);
	}
	
	public int getTotalCredits() {
		int totalCredits = 0;
		for (Integer credits : creditsMap.values()) {
		    totalCredits += credits;
		}
		return totalCredits;	
	}
	
	public float getCGPA() {
		int credits = 0, totalCredits = 0;
		float sgpa = 0.0F, cgpa = 0.0F;
		for (String semester : sgpaMap.keySet()) {
		    credits = creditsMap.get(semester);
		    sgpa = sgpaMap.get(semester);
		    cgpa += sgpa*credits;
		    totalCredits += credits;
		}
		return cgpa/totalCredits;
	}
}