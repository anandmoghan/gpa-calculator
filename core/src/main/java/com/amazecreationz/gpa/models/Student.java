package com.amazecreationz.gpa.models;

import com.amazecreationz.gpa.constants.StudentConstants;
import com.amazecreationz.gpa.services.GPAService;
import com.google.gson.JsonObject;


/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 26/5/17 3:07 PM.
 */
public class Student implements StudentConstants {
    private String name;
    private String rollNo;
    private String branchCode;
    private JsonObject semesterInfo;
    private int totalPoints;
    private int totalPassedCredits;
    private int totalFailedCredits;
    private int totalOtherCredits;

    public Student() {
        this.name = null;
        this.rollNo = null;
        this.branchCode = null;
        this.semesterInfo = new JsonObject();
        this.totalPoints = 0;
        this.totalPassedCredits = 0;
        this.totalFailedCredits = 0;
        this.totalOtherCredits = 0;
    }

    public void putStudentInfo(String name, String rollNo, String branchCode) {
        this.name = name;
        this.rollNo = rollNo;
        this.branchCode = branchCode;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void putSemesterData(String semester, int gradePoints, int passedCredits, int failedCredits, int otherCredits) {
        JsonObject semesterData = new JsonObject();
        double sgpa = (gradePoints * 100 /passedCredits);
        sgpa /= 100;
        totalPoints += gradePoints;
        totalPassedCredits += passedCredits;
        totalFailedCredits += failedCredits;
        totalOtherCredits += otherCredits;
        semesterData.addProperty(SGPA, sgpa);
        semesterData.addProperty(PASSED_CREDITS, passedCredits);
        semesterData.addProperty(FAILED_CREDITS, failedCredits);
        semesterData.addProperty(OTHER_CREDITS, otherCredits);
        semesterInfo.add(semester, semesterData);
    }

    private double getCGPA() {
        double cgpa = totalPoints *100 /totalPassedCredits;
        return cgpa/100;
    }

    public JsonObject getAsJSON() {
        JsonObject studentJSON = new JsonObject();
        studentJSON.addProperty(NAME, name);
        studentJSON.addProperty(ROLL_NO, rollNo);
        studentJSON.addProperty(BRANCH_CODE, branchCode);
        studentJSON.addProperty(BRANCH, GPAService.getBranch((branchCode)));
        studentJSON.add(SEMESTER_INFO, semesterInfo);
        studentJSON.addProperty(PASSED_CREDITS, totalPassedCredits);
        studentJSON.addProperty(FAILED_CREDITS, totalFailedCredits);
        studentJSON.addProperty(OTHER_CREDITS, totalOtherCredits);
        studentJSON.addProperty(TOTAL_CREDITS, totalPassedCredits + totalOtherCredits);
        studentJSON.addProperty(CGPA, getCGPA());
        return studentJSON;
    }
}
