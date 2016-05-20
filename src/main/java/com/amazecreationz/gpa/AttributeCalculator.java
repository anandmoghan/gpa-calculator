//$Id$
package com.amazecreationz.gpa;

public class AttributeCalculator {
	public static int getGradePoint(char grade){
        switch(grade){
            case 'S':return 10;
            case 'A':return 9;
            case 'B':return 8;
            case 'C':return 7;
            case 'D':return 6;
            case 'E':return 5;
            case 'F':return 0;
            case 'P':return 0;
        }
        return 0;
    }
	
	public static String getBranch(String branch){
		switch(branch){
			case "EC": return "Electronics and Communication Engineering";
			case "EE": return "Electrical and Electronics Engineering";
			case "CS": return "Computer Science Engineering";
			case "ME": return "Mechanical Engineering";
			case "CE": return "Civil Engineering";
			case "CH": return "Chemical Engineering";
			case "PE": return "Production Engineering";
			case "EP": return "Engineering Physics";
			case "BT": return "Biotechnology";
		}
        return branch;
    }
}
