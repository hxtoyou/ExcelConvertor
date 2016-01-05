package pactera;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年8月18日 下午4:54:01
 * 
 */
public class TestDemo {
    public static void main(String[] args) throws Exception { 
        String file = "src\\test\\java\\pactera\\book.xml"; 
        int index = file.lastIndexOf("\\test\\");
        System.out.println("index:"+file.substring(0,index));
        
    } 
}
