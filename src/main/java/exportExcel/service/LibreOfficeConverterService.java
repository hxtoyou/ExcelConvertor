package exportExcel.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import exportExcel.utils.PropertiesUtils;

/**
 * 基于 libreoffice的转化工具服务
 * 
 * @author lixia
 * 
 */
@Service
public class LibreOfficeConverterService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private String libreOfficePath;

	public LibreOfficeConverterService() {
		this.setLibreOfficePath(PropertiesUtils.getString("libreOfficePath"));
	}

	public void service(String sourceFile, String destFile) {
	}

	public static void main(String[] args) {
		File file = new File(PropertiesUtils.getString("libreOfficePath"));
	}

	public String getLibreOfficePath() {
		return libreOfficePath;
	}

	public void setLibreOfficePath(String libreOfficePath) {
		this.libreOfficePath = libreOfficePath;
	}

}
