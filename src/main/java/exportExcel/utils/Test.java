package exportExcel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年7月20日 下午5:55:45
 * 
 */
public class Test {

	public static void main(String args[]) throws IOException{
	String printName="Samsung K2200 Series";
	  List<String> processList = new ArrayList<String>();
	  File file = File.createTempFile("realhowto",".vbs");
        file.deleteOnExit();
        FileWriter fw = new java.io.FileWriter(file);
        String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                   + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
                   + "Set service = locator.ConnectServer()\n"
                   + "Set processes = service.ExecQuery _\n"
                   + " (\"select name from Win32_Process WHERE Name like '%" + printName + "%'\")";
    fw.write(vbs);
    fw.close();
    Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
    BufferedReader input =
        new BufferedReader
          (new InputStreamReader(p.getInputStream()));
    String line;
    while ((line = input.readLine()) != null) {
        processList.add(line);
    }
    input.close();

	String result = "";
	
	Iterator<String> it = processList.iterator();
	int i = 0;
	while (it.hasNext()) {
	   result += it.next() +",";
	   i++;
	   if (i==10) {
	       result += "\n";
	       i = 0;
	   }
	}
	System.out.println("result:"+result);
}

}
