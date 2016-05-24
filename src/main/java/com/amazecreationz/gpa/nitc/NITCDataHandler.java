//$Id$
package com.amazecreationz.gpa.nitc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NITCDataHandler {
	public boolean cleanFile(String inputFileName, String outputFileName)
    {
        BufferedWriter out;
        String copy = null;
        try (BufferedReader in = new BufferedReader(new FileReader(inputFileName))) {
            out = new BufferedWriter(new FileWriter(outputFileName));
            /*Unwanted Strings to be removed Start*/
            String s1 = "Sl No. Code Course Title Credits Grade Result";
            String s2 = "National Institute of Technology, Calicut";
            String s3 = "Page";
            String s4 = "ActiveReports Evaluation. Copyright 2002-2005 (c) Data Dynamics, Ltd. All Rights Reserved.";
            /*Unwanted Strings to be removed End*/
            String line;
            int c = 0;
            while((line=in.readLine())!=null && line.length()!=0){
                if((line.compareTo(s2)==0)){
                    c++;
                }
                if((line.compareTo(s1)!=0)&&(line.compareTo(s2)!=0)&&(line.substring(0,4).compareTo(s3)!=0)&&(line.compareTo(s4)!=0)){
                    if(line.substring(0,3).compareTo("Sem")!=0)
                        out.write(copy+String.format("%n"));
                    copy=line;
                }
            }
            out.close();
            if(c==0){
                System.out.println("Wrong Input File!");
                return false;
            }
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
        BufferedWriter out;
        String details = "";
        try (BufferedReader in = new BufferedReader(new FileReader(inputFileName))) {
        	out = new BufferedWriter(new FileWriter(outputFileName));
            String line;
            in.readLine();
            while((line=in.readLine())!=null && line.length()!=0){
                if(line.compareTo(details)==0){
                    continue;
                }
                if((line.substring(0,3).compareTo("Sem")==0)){
                    out.write(line+String.format("%n"));
                    continue;
                }
                if((line.lastIndexOf("PASS"))>0||(line.lastIndexOf("FAIL"))>0)
                    out.write(line+String.format("%n"));
                else
                    out.write(line+" ");
            }
            out.close();
            return true;
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
    }
	
	public boolean dataProcessor(String inputFileName){
		
		if(cleanFile(inputFileName, "tmp//clean.txt") && alignData("tmp//clean.txt", "tmp//align.txt")){
			return true;
		}
		return false;
	}
}
