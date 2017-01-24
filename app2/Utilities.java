package app2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.stage.Stage;

public class Utilities {

	public static boolean checkPass(String p) {
		if (p.matches(".*\\d+.*") && p.matches(".*[a-zA-Z]+.*") && p.length() > 4)
			return true;
		return false;
	}

	public static boolean checkLogin(String user, String pass) {
		String p = Database.getPassword(user);
		if (p == null)
			return false;
		if (p.equals(pass))
			return true;
		return false;
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public static void centerStage(Stage stage, int x, int y) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		stage.setX(d.width / 2 - (x / 2));
		stage.setY(d.height / 2 - (y / 2));
	}

	public static boolean isNaN(double x) {
		return x != x;
	}

	public static void toPDF(String filename, String q1, String iq1, String q2, String iq2, String q3, String iq3,
			String q4, String iq4, String q5, String iq5, String q6, String iq6, String q7, String iq7, String q8,
			String iq8, String q9, String iq9, String q10, String iq10, String q11, String iq11, String q12,
			String iq12) throws DocumentException, IOException {
		Document document = new Document();
		
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		
		document.open();
		
		document.add(new Paragraph("Chestionar"));
		document.add(new Paragraph(q1));
		document.add(new Paragraph(iq1));
		document.add(new Paragraph(q2));
		document.add(new Paragraph(iq2));
		document.add(new Paragraph(q3));
		document.add(new Paragraph(iq3));
		document.add(new Paragraph(q4));
		document.add(new Paragraph(iq4));
		document.add(new Paragraph(q5));
		document.add(new Paragraph(iq5));
		document.add(new Paragraph(q6));
		document.add(new Paragraph(iq6));
		document.add(new Paragraph(q7));
		document.add(new Paragraph(iq7));
		document.add(new Paragraph(q8));
		document.add(new Paragraph(iq8));
		document.add(new Paragraph(q9));
		document.add(new Paragraph(iq9));
		document.add(new Paragraph(q10));
		document.add(new Paragraph(iq10));
		document.add(new Paragraph(q11));
		document.add(new Paragraph(iq11));
		document.add(new Paragraph(q12));
		document.add(new Paragraph(iq12));
		
		document.close();
	}
}
