package com.amazecreationz.gpa.services;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 26/5/17 1:29 PM.
 */

public class PDFService {
    private String inputPDF;

    public PDFService(String inputPDF) {
        this.inputPDF = inputPDF;
    }

    public String extractText() {
        try {
            PDFParser pdfparser = new PDFParser(new RandomAccessFile(new File(inputPDF), "r"));
            pdfparser.parse();
            return new PDFTextStripper().getText(new PDDocument(pdfparser.getDocument()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
