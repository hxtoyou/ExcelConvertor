package exportExcel.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import exportExcel.utils.PropertiesUtils;

@Service
public class PDF2SwfService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public int convertPDF2SWF(String sourcePath, String destPath, String fileName) throws IOException {

		File dest = new File(destPath);

		if (!dest.exists()) {

			dest.mkdirs();

		}

		// 源文件不存在则返回

		File source = new File(sourcePath);

		if (!source.exists()) {

			return -1;

		}
		String languagePath = PropertiesUtils.getString("languagePath");
		String pdf2swfPath = PropertiesUtils.getString("pdf2swfPath");
		String command = pdf2swfPath + " -z -s flashversion=9 \"" + sourcePath + "\" -o \"" + destPath + fileName
				+ "\" -s " + " languagedir=" + languagePath;
		logger.debug(command);
		Process pro = Runtime.getRuntime().exec(command);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		String temp = null;
		while ((temp = bufferedReader.readLine()) != null) {
			logger.debug(temp);
		}
		try {
			pro.waitFor();

		} catch (InterruptedException e) {

			e.printStackTrace();

		}
		return pro.exitValue();
	}
}
