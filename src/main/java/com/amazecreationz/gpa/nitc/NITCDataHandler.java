//$Id$
package com.amazecreationz.gpa.nitc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.amazecreationz.gpa.common.AppConstants;
import com.amazecreationz.gpa.common.AttributeCalculator;
import com.amazecreationz.gpa.common.CommonService;
import com.amazecreationz.gpa.common.StudentData;

public class NITCDataHandler implements AppConstants {
	private String inputFileName;
	private String rootLocation;
	private String baseDirectory;
	private String reservedDirectory;
	private StudentData student;
	
	public NITCDataHandler (String fileName) {
		this.inputFileName = fileName;
		this.rootLocation = System.getProperty("user.dir");
		this.baseDirectory = this.rootLocation +"/GPACalcData/";
		this.reservedDirectory = this.baseDirectory +new Date().getTime();
	}
	
	public NITCDataHandler (String fileName, String rootLoc) {
		this.inputFileName = fileName;
		this.rootLocation = rootLoc;
		this.baseDirectory = this.rootLocation +"/GPACalcData/";
		this.reservedDirectory = this.baseDirectory +new Date().getTime();
	}
	
	public boolean setStudentData(String studentDetails) {
		int len = studentDetails.length();
		String studentName = studentDetails.substring(0, len-11);
		String rollNo = studentDetails.substring(len-9, len);
		String branchCode = studentDetails.substring(len-2, len);
		student = new StudentData(studentName, rollNo, AttributeCalculator.getBranch(branchCode));
		return true;
	}
	
	public StudentData getStudentData() {
		return student;
	}
	
	public boolean cleanFile(String inputFileName, String outputFileName)
    {
		BufferedReader in;
		BufferedWriter out;
        try {
        	in = new BufferedReader(new FileReader(inputFileName));
            out = new BufferedWriter(new FileWriter(outputFileName));
            /*Unwanted Strings to be removed Start*/
            String s1 = "National Institute of Technology, Calicut";
            String s2 = "Sl No. Code Course Title Credits Grade Result";
            String s3 = "Page";
            String s4 = "ActiveReports Evaluation.";
            /*Unwanted Strings to be removed End*/
            String line, copy = null;
            int c = 0;
            while((line = in.readLine()) != null && line.length() != 0){
                if(line.startsWith(s1)){
                    c++;
                }
                if(!line.startsWith(s1) && !line.startsWith(s2) && !line.startsWith(s3) && !line.startsWith(s4)){
                    if(!line.startsWith("Semester")){
                        out.write(copy + String.format("%n"));
                    }
                    copy=line;
                }
            }
            in.close();
            out.close();
            if(c == 0){
                System.out.println("Wrong Input File!");
                return false;
            }
            setStudentData(copy);
            return true;
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
    }
	
	public boolean alignData(String inputFileName, String outputFileName)
    { 
		BufferedReader in;
		BufferedWriter out;
        try {
        	in = new BufferedReader(new FileReader(inputFileName));
        	out = new BufferedWriter(new FileWriter(outputFileName));
            String line;
            in.readLine();
            while((line = in.readLine()) !=null && line.length() != 0){
                if(line.startsWith(student.getName())){
                }
                else if(line.startsWith("Semester") || line.lastIndexOf("PASS") > 0 || line.lastIndexOf("FAIL") > 0){
                    out.write(line + String.format("%n"));
                }
                else{
                    out.write(line + " ");
                }
            }
            in.close();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
    }
	
	public boolean calculateGPA(String inputFileName){
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(inputFileName));
			String line;
			Semester semester = Semester.S1;
			float sgpa = 0.0F;
			int passCredits = 0, failedCredits = 0, semesterIndex = 0;
			while((line = in.readLine()) != null && line.length() != 0){
				if(line.startsWith("Semester")){
					if(sgpa > 0){
						student.setSGPA(semester, sgpa/passCredits);
						student.setCredits(semester, passCredits);
						student.setPassCredits(passCredits);
						student.setFailedCredits(failedCredits);						
					}
					semester = Semester.values()[semesterIndex];
					sgpa = 0.0F;
					passCredits = 0;
					failedCredits = 0;
					semesterIndex++;
				}
				else {
					String args [] = line.split(" ");
					int argsLen = args.length;
					int credits = Integer.parseInt(args[argsLen-3]);
					if(args[argsLen-1].startsWith("PASS")){
						if(args[argsLen-2].startsWith("P")){
							student.setPassCredits(credits);
						}
						else{
							sgpa += (float) credits * AttributeCalculator.getGradePoint(args[argsLen-2]);
							passCredits += credits;	
						}			
					}
					else {
						failedCredits += credits;
					}
				}
			}
			student.setSGPA(semester, sgpa/passCredits);
			student.setCredits(semester, passCredits);
			student.setPassCredits(passCredits);
			student.setFailedCredits(failedCredits);
			in.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean dataProcessor(){
		System.out.println("NITC GradeCard");
		System.out.println("Working Directory: " +this.rootLocation);
		if(CommonService.createDirectory(this.baseDirectory)){
			if(CommonService.createDirectory(reservedDirectory)){
				if(CommonService.stripTextFromPDF(inputFileName, reservedDirectory+"/strippedtxt.txt")){
					if(cleanFile(reservedDirectory+"/strippedtxt.txt", reservedDirectory+"/clean.txt") && alignData(reservedDirectory+"//clean.txt", reservedDirectory+"//align.txt") && calculateGPA(reservedDirectory+"//align.txt")){
						System.out.println("CGPA: "+ student.getCGPA());
						return true;
					}
				}
			}
		}
		return false;
	}
}
