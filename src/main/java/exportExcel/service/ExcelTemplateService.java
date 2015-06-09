/*
 * Copyright (C) 2012 CCRISE.
 */
package exportExcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exportExcel.ORM.DAO.HibernateDAO;
import exportExcel.ORM.service.HibernateDataServiceImpl;
import exportExcel.access.ExcelTemplateDAO;
import exportExcel.entity.ExcelTemplate;

/**
 * ExcelTemplate Service。
 * 
 * @author Page(hxtoyou@gmail.com)
 */
@Service
public class ExcelTemplateService extends HibernateDataServiceImpl<ExcelTemplate, Long> {
	@Autowired
	private ExcelTemplateDAO excelTemplateDAO;

	@Override
	public HibernateDAO<ExcelTemplate, Long> getDAO() {
		return excelTemplateDAO;
	}
}