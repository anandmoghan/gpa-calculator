package com.amazecreationz.gpa;

import com.amazecreationz.gpa.constants.GPAConstants;
import com.amazecreationz.gpa.services.NITCService;

public class GPAExecutor implements GPAConstants {
	private static void executeSample() {
		System.out.println(new NITCService(sampleCard).process().getAsJSON());
	}
	
	public static void main(String[] args) {
		executeSample();
	}
}