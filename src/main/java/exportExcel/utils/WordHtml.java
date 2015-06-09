package exportExcel.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

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
	 
	public static void main(String[] args) throws Exception{
//		DataConvert dataConvert = new DataConvert();
//		System.out.println(dataConvert.setVal(1));
//		System.out.println(dataConvert.setVal(3));
		String o =String.valueOf("0703220000013171");
		System.out.println(o);
	}
}
