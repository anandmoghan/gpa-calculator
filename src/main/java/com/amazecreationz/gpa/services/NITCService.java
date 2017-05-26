package com.amazecreationz.gpa.services;

import com.amazecreationz.gpa.models.Student;
import com.amazecreationz.gpa.constants.GPAConstants;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 26/5/17 1:14 PM.
 */

public class NITCService implements GPAConstants {
    private String inputFileName;
    private Student student;

    public NITCService(String inputFileName) {
        this.inputFileName = inputFileName;
        this.student = new Student();
    }

    private boolean isCourseEnd(String line) {
        return line.endsWith("PASS") || line.endsWith("FAIL");
    }

    private boolean isUnnecessary(String line) {
        boolean result = line.startsWith("National Institute of Technology, Calicut");
        result = result || line.startsWith("ActiveReports Evaluation.");
        result = result || line.startsWith("Page No :");
        result = result || line.startsWith("Sl No.");
        result = result || line.matches("[^\\s]+\\sSemester\\s\\d{3,}-\\d{3,}");
        return result;
    }

    private int getGradePoint(String grade) {
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

    public Student process() {
        String gradeCardText = new PDFService(inputFileName).extractText();
        if(gradeCardText == null) {
            return null;
        }
        String[] lines = gradeCardText.split("\n");
        String currentSemester = null;
        String nextSemester;
        String fullLine = null;
        String compute = null;
        int gradePoints = 0;
        int passedCredits = 0;
        int failedCredits = 0;
        int otherCredits = 0;
        for(String line : lines) {
            if(line.startsWith("Semester")) {
                nextSemester = GPAService.getSemesterTitle(line.split(" : ")[1]);
                if(currentSemester != null) {
                    if(!nextSemester.equals(currentSemester)) {
                        student.putSemesterData(currentSemester, gradePoints, passedCredits, failedCredits, otherCredits);
                        gradePoints = 0;
                        passedCredits = 0;
                        failedCredits = 0;
                        otherCredits = 0;
                    }
                }
                currentSemester = nextSemester;
            } else if (line.length() > 8 && GPAService.isNumber(line.substring(0,1))) {
                if(isCourseEnd(line)) {
                    compute = line;
                } else {
                    fullLine = line;
                }
            } else if(fullLine != null) {
                fullLine += line;
                if(isCourseEnd(line)) {
                    compute = fullLine;
                    fullLine = null;
                }
            } else if (!isUnnecessary(line) && line.length() > 0 && student.getName() == null) {
                String[] data = line.split("\\s{2,}");
                int dataLength = data[1].length();
                String name = data[0];
                String rollNo = data[1];
                String branchCode = data[1].substring(dataLength-2, dataLength);
                student.putStudentInfo(name, rollNo, branchCode);
            }

            if(compute != null) {
                int computeLength = compute.length();
                int credit = Integer.parseInt(compute.substring(computeLength-8, computeLength-7));
                int grade = getGradePoint(compute.substring(computeLength-6, computeLength-5));
                boolean isPass = compute.substring(computeLength-4, computeLength).startsWith("PASS");
                gradePoints += grade*credit;
                passedCredits += isPass && grade != 0 ? credit : 0;
                failedCredits += isPass ? 0 : credit;
                otherCredits += isPass && grade == 0 ? credit : 0;
                compute = null;
            }
        }
        student.putSemesterData(currentSemester, gradePoints, passedCredits, failedCredits, otherCredits);
        return student;
    }
}
