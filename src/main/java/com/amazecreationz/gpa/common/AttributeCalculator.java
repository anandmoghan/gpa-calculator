//$Id$
package com.amazecreationz.gpa.common;

public class AttributeCalculator {
	public static int getGradePoint(String grade){
        switch(grade){
            case "S":return 10;
            case "A":return 9;
            case "B":return 8;
            case "C":return 7;
            case "D":return 6;
            case "E":return 5;
            case "F":return 0;
            case "P":return 0;
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
			case "AR": return "Architecture";
		}
        return branch;
    }
	
	public static String getBranchCode(String branch){
		switch(branch){
			case "Electronics and Communication Engineering": return "EC";
			case "Electrical and Electronics Engineering": return "EE";
			case "Computer Science Engineering": return "CS";
			case "Mechanical Engineering": return "ME";
			case "Civil Engineering": return "CE";
			case "Chemical Engineering": return "CH";
			case "Production Engineering": return "PE";
			case "Engineering Physics": return "EP";
			case "Biotechnology": return "BT";
			case "Architecture": return "AR";
		}
        return branch;
    }
}
