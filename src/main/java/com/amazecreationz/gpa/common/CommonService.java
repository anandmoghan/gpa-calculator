package com.amazecreationz.gpa.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class CommonService {
	public static boolean createDirectory(String dirName) {
		File dir = new File(dirName);
		if(!dir.exists()) {
			if(dir.mkdir()) {
				System.out.println(dirName+" is created");
				return true;
			}
			else {
				System.out.println(dirName+" is not created");
				return false;
			}
		}
		return true;		
	}
	
	public static boolean stripTextFromPDF (String inputFileName, String outputFileName) {
		try {
			PDFParser pdfparser = new PDFParser(new RandomAccessFile(new File(inputFileName), "r"));
			pdfparser.parse();
			COSDocument cosdocument = pdfparser.getDocument();
			PrintWriter printwriter = new PrintWriter(outputFileName);
			printwriter.print(new PDFTextStripper().getText(new PDDocument(cosdocument)));
			printwriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
