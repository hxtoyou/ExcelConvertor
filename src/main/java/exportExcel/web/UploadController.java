/*
 * Copyright (C) CCRISE.
 */
package exportExcel.web;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import exportExcel.entity.UploadedFile;
import exportExcel.service.LibreOfficeConverterService;
import exportExcel.service.PDF2SwfService;
import exportExcel.service.UploadedFileService;
import exportExcel.utils.JavaImageUtil;
import exportExcel.utils.Response;

/**
 * 文件上传Controller。
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
@Controller
public class UploadController {
	public static final String DEFAULT_PATH = "/WEB-INF/uploads/template/";
	private static final int RANDOM_NUMERIC_COUNT = 6;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private String defaultUploadPath = DEFAULT_PATH;
	@Autowired
	private LibreOfficeConverterService document2PDFConvertService;
	@Autowired
	private PDF2SwfService pDF2SwfService;
	@Autowired
	private UploadedFileService uploadedFileService;

	@RequestMapping(value = "/simpleupload", method = RequestMethod.POST)
	public void simpleUpload(@RequestParam MultipartFile file, String callBackFunction, HttpSession httpSession,
			HttpServletResponse response, final String uploadPath) throws IOException {
		String callBack = StringUtils.isBlank(callBackFunction) ? "callBack" : callBackFunction;
		// 生成文件路径
		String filePath = generatePath(file);

		// 自定义上传目录
		if (!Strings.isNullOrEmpty(uploadPath)) {
			if (uploadPath.charAt(uploadPath.length() - 1) != '/') {
				defaultUploadPath = uploadPath + '/';
			} else {
				defaultUploadPath = uploadPath;
			}
		} else {
			defaultUploadPath = DEFAULT_PATH;
		}

		// 获取上传目录的真实路径
		String uploadRealPath = httpSession.getServletContext().getRealPath(defaultUploadPath);
		filePath = replaceChars(filePath);
		// 写入文件
		final File newFile = new File(uploadRealPath + "/" + filePath);
		FileUtils.writeByteArrayToFile(newFile, file.getBytes());
		/**
		 * 图片上传处理过程
		 */
		String lowerFileName = filePath.toLowerCase();
		/*
		 * // 非图片 office文档 的上传文件 if (lowerFileName.endsWith("cad") ||
		 * lowerFileName.endsWith("dwg") || lowerFileName.endsWith("dxf") ||
		 * lowerFileName.endsWith("rar") || lowerFileName.endsWith("zip")) { //
		 * 记录日志 logEntityServiceImpl.info("上传文件：" + file.getOriginalFilename() +
		 * "，目录：" + defaultUploadPath + filePath); // 设置响应 UploadedFile instance
		 * = new UploadedFile();
		 * instance.setSimpleName(file.getOriginalFilename());
		 * instance.setFilePath(defaultUploadPath.replaceFirst("/WEB-INF", "") +
		 * filePath); uploadedFileService.save(instance);
		 * response.setContentType("text/html"); response.getWriter().write(
		 * "<script>parent." + callBack + "(" + JSON.toJSONString(new
		 * Response(instance)) + ")</script>"); response.flushBuffer(); return;
		 * } if (lowerFileName.endsWith("jpg") || lowerFileName.endsWith("jpeg")
		 * || lowerFileName.endsWith("bmp") || lowerFileName.endsWith("gif") ||
		 * lowerFileName.endsWith("png")) { // 记录日志
		 * logEntityServiceImpl.info("上传文件：" + file.getOriginalFilename() +
		 * "，目录：" + defaultUploadPath + filePath);
		 * 
		 * this.compressPic(newFile.getAbsolutePath(),
		 * newFile.getAbsolutePath()); // 设置响应
		 * response.setContentType("text/html"); response.getWriter().write(
		 * "<script>parent." + callBack + "(" + JSON.toJSONString(new
		 * Response(new String( (defaultUploadPath.replaceFirst("/WEB-INF", "")
		 * + filePath)))) + ")</script>"); response.flushBuffer(); return; }
		 */
		// 文档上传
		UploadedFile instance = null;
		try {
			instance = fileUploadFilter(file.getOriginalFilename(), filePath, newFile);
		} catch (Exception e) {
			// 设置响应
			response.setContentType("text/html");
			Response falseResponse = new Response();
			falseResponse.setSuccess(false);
			falseResponse.setData(e.getMessage());
			response.getWriter().write(
					"<script>parent." + callBack + "(" + JSON.toJSONString(falseResponse) + ")</script>");
			response.flushBuffer();
			return;
		}
		// 记录日志
		// logEntityServiceImpl.info("上传文件：" + file.getOriginalFilename() +
		// "，目录：" + defaultUploadPath + filePath);
		// 设置响应
		response.setContentType("text/html");

		response.getWriter().write(
				"<script>parent." + callBack + "(" + JSON.toJSONString(new Response(instance)) + ")</script>");
		response.flushBuffer();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(@RequestParam MultipartFile file, HttpSession httpSession, HttpServletResponse response,
			final String uploadPath) throws IOException {
		// 生成文件路径
		String filePath = generatePath(file);

		// 自定义上传目录
		if (!Strings.isNullOrEmpty(uploadPath)) {
			if (uploadPath.charAt(uploadPath.length() - 1) != '/') {
				defaultUploadPath = uploadPath + '/';
			} else {
				defaultUploadPath = uploadPath;
			}
		} else {
			defaultUploadPath = DEFAULT_PATH;
		}

		// 获取上传目录的真实路径
		String uploadRealPath = httpSession.getServletContext().getRealPath(defaultUploadPath);
		filePath = replaceChars(filePath);
		// 写入文件
		final File newFile = new File(uploadRealPath + "/" + filePath);
		FileUtils.writeByteArrayToFile(newFile, file.getBytes());
		// 记录日志
		// logEntityServiceImpl.info("上传文件：" + file.getOriginalFilename() +
		// "，目录：" + defaultUploadPath + filePath);

		// 设置响应
		response.setContentType("text/html");
		response.getWriter()
				.write(JSON.toJSONString(new Response(new String(
						(defaultUploadPath.replaceFirst("/WEB-INF", "") + filePath)))));
		response.flushBuffer();
	}

	/**
	 * 文档上传的过滤操作
	 * 
	 * @param filePath
	 * @param newFile
	 * @return
	 * @throws IOException
	 */
	private UploadedFile fileUploadFilter(String sourceName, String filePath, final File newFile) throws Exception {
		String uploadFolder = getUploadFolder();
		String fullName = newFile.getAbsolutePath();
		String pdfRealPath = null;
		String pdfPath = null;
		fullName = fullName.toLowerCase();
		if (!fullName.endsWith(".pdf")) {
			pdfRealPath = fullName + ".pdf";
		} else {
			pdfRealPath = fullName;
		}
		pdfPath = defaultUploadPath.replaceFirst("/WEB-INF", "") + filePath + ".pdf";
		UploadedFile instance = new UploadedFile();
		instance.setPdfPath(pdfPath);
		instance.setFilePath(defaultUploadPath.replaceFirst("/WEB-INF", "") + filePath);
		instance.setAddTime(new Timestamp(System.currentTimeMillis()));
		uploadedFileService.save(instance);
		instance.setSimpleName(sourceName);
		// try {
		// if (!fullName.endsWith(".pdf")) {
		// /**
		// * 转化pdf
		// */
		// System.out.println("开始转化pdf：" + fullName);
		// document2PDFConvertService.service(fullName, pdfRealPath);
		// System.out.println("转化pdf完毕：" + pdfRealPath);
		// }
		// /**
		// * 转化swf
		// */
		// System.out.println("开始转化swf：" + pdfRealPath);
		// pDF2SwfService.convertPDF2SWF(pdfRealPath,
		// newFile.getParentFile().getAbsolutePath() + "\\",
		// instance.getId() + ".swf");
		// System.out.println("转化swf完毕：");
		// } catch (Exception e) {
		// throw new Exception("服务启动失败" + e.getMessage());
		// }
		// instance.setSwfPath(uploadFolder + "/" + instance.getId() + ".swf");
		logger.debug("instance:{}", instance);
		uploadedFileService.save(instance);
		return instance;
	}

	/**
	 * 
	 */
	private void compressPic(String inputDir, String outputDir) {
		JavaImageUtil util = new JavaImageUtil();
		util.compressPic(inputDir, outputDir);
	}

	private String generatePath(MultipartFile file) {
		Timestamp now = new Timestamp(System.currentTimeMillis());

		// 根据年月生成文件夹
		String folder = new SimpleDateFormat("yyyy-MM").format(now);

		// 重新生成文件名，文件名+日期+时间+6位随机数
		String fullFileName = file.getOriginalFilename();

		String fileName, type = "";
		if (fullFileName.lastIndexOf(".") != -1) {
			fileName = fullFileName.substring(0, fullFileName.lastIndexOf("."));
			type = fullFileName.substring(fullFileName.lastIndexOf("."));
		} else {
			fileName = fullFileName;
		}

		String newFullFileName = fileName + "-" + new SimpleDateFormat("ddHHmmss").format(now) + "-"
				+ RandomStringUtils.randomNumeric(RANDOM_NUMERIC_COUNT) + type.toLowerCase();

		return folder + "/" + newFullFileName;
	}

	private String getUploadFolder() {
		return DEFAULT_PATH.replaceFirst("/WEB-INF", "")
				+ new SimpleDateFormat("yyyy-MM").format(new Timestamp(System.currentTimeMillis()));
	}

	private String replaceChars(String srcString) {
		return srcString.replace("[", "【").replace("]", "】");
	}
}
