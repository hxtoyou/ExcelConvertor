package exportExcel.utils;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPageable;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import com.google.common.base.Strings;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年5月29日 下午6:38:18
 * 
 */
public class WordHtml {
	public static void docxToHtml(String filepath, String outpath) throws Docx4JException, FileNotFoundException{
	    WordprocessingMLPackage wmp = WordprocessingMLPackage.load(new File(filepath));
	    Docx4J.toHTML(wmp,"","", new FileOutputStream(new File(outpath)));
	}
	 
	  public static void main(String[] args) throws IllegalArgumentException, PrinterException, IOException, PrintException {
			DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
		   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		   DocAttributeSet das = new HashDocAttributeSet();
		   aset.add(MediaSizeName.ISO_A4);
		   PrintService[] pservices =
		                 PrintServiceLookup.lookupPrintServices(flavor, null);
		   System.out.println("pservices.length:"+pservices[0].getName());
	    }
}
