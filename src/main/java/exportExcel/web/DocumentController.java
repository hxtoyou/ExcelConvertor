/*
 * Copyright (C) 2010-2020 CCRISE.
 */
package exportExcel.web;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import exportExcel.entity.Document;
import exportExcel.service.DocumentService;
import exportExcel.service.UploadedFileService;
import exportExcel.utils.Response;

/**
 * Document Controller。
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
@Controller
public class DocumentController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DocumentService documentService;
	@Autowired
	private UploadedFileService uploadService;

	@RequestMapping(value = "/spmi/document/common", method = RequestMethod.GET)
	public ModelAndView commonPage(String code) {
		return modelAndView(code);
	}

	@RequestMapping(value = "/spmi/document/move", method = RequestMethod.GET)
	@ResponseBody
	public Response index(Long docId, Long folderId) {
		return new Response(documentService.moveDocument(docId, folderId));
	}

	@RequestMapping(value = "/spmi/document/documents/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Response delete(@PathVariable long id) {
		return new Response(documentService.delete(id));
	}

	@RequestMapping(value = "/spmi/document/documents", method = RequestMethod.POST)
	@ResponseBody
	public Response save(String documentName, String keyWord, Integer securityLevel, Document document,
			Long attachmentId) {
		Timestamp current = new Timestamp(System.currentTimeMillis());
		document.setCreateTime(current);
		document.setLastModifyTime(current);
		document.setAttachment(uploadService.get(attachmentId));
		return new Response(documentService.save(document));
	}

	private ModelAndView modelAndView(String tag) {
		ModelAndView modelAndView = new ModelAndView("spmi/document/index");
		modelAndView.addObject("office", tag);
		return modelAndView;
	}
}