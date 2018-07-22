package com.amazecreationz.gpa.services;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 26/5/17 1:53 PM 6:25 PM.
 */

public class GPAService {
    public static String getSemesterTitle(int semesterIndex) {
        return "SEMESTER_"+semesterIndex;
    }

    public static String getSemesterTitle(String semesterIndex) {
        return "SEMESTER_"+semesterIndex;
    }

    public static boolean isNumber(String number) {
        return number != null && number.matches("[-+]?\\d*\\.?\\d+");
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
