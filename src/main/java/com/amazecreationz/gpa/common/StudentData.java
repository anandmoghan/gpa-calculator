//$Id$
package com.amazecreationz.gpa.common;

import java.util.HashMap;

public class StudentData implements AppConstants {
	private String name, rollNo, branch;
	private int passCredits, failedCredits;
	private HashMap<Semester, Float> sgpaMap;
	private HashMap<Semester, Integer> creditsMap;
	
	public StudentData(String name, String rollNo, String branch){
		this.name = name;
		this.rollNo = rollNo;
		this.branch = branch;
		this.passCredits = 0;
		this.failedCredits = 0;
		this.sgpaMap = new HashMap<Semester, Float>();
		this.creditsMap = new HashMap<Semester, Integer>();
	}
	
	public void setPassCredits(int passCredits) {
		this.passCredits += passCredits;
	}
	
	public void setFailedCredits(int failedCredits) {
		this.failedCredits += failedCredits;
	}
	
	public void setSGPA(Semester semester, Float sgpa) {
		sgpaMap.put(semester, sgpa);
	}
	
	public void setCredits(Semester semester, Integer credits) {
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
	
	public float getSGPA(Semester semester) {
		return sgpaMap.get(semester);
	}
	
	public int getCredits(Semester semester) {
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
		for (Semester semester : sgpaMap.keySet()) {
		    credits = creditsMap.get(semester);
		    sgpa = sgpaMap.get(semester);
		    cgpa += sgpa*credits;
		    totalCredits += credits;
		}
		return cgpa/totalCredits;
	}
}