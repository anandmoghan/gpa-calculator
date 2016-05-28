package com.amazecreationz.gpa;

import com.amazecreationz.gpa.nitc.NITCDataHandler;

public class GPAExecutor {	
	public static void executeSample() {	
		new NITCDataHandler("sample//GradeCard.pdf").dataProcessor();
	}
	
	public static void main(String[] args) {
		executeSample();
	}
}