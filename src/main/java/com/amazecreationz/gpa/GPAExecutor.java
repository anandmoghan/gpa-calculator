package com.amazecreationz.gpa;

import com.amazecreationz.gpa.common.StudentData;
import com.amazecreationz.gpa.nitc.NITCDataHandler;

public class GPAExecutor {	
	public static void executeSample() {	
		new NITCDataHandler("sample//GradeCard.pdf").dataProcessor();
	}
	
	public static void main(String[] args) {
		StudentData student =  new StudentData("Anand Mohan", "B110078EC", "EC");
		System.out.println(student.getName());
		executeSample();
	}
}