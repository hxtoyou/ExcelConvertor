/*
 * Copyright (C) 2012 CCRISE.
 */
package exportExcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exportExcel.ORM.DAO.HibernateDAO;
import exportExcel.ORM.service.HibernateDataServiceImpl;
import exportExcel.access.IMLCISSUEARDAO;
import exportExcel.entity.IMLCISSUEAR;

/**
 * ExcelTemplate Service。
 * 
 * @author Page(hxtoyou@gmail.com)
 */
@Service
public class IMLCISSUEARService extends HibernateDataServiceImpl<IMLCISSUEAR, Long> {
	@Autowired
	private IMLCISSUEARDAO imlcissueardao;

	@Override
	public HibernateDAO<IMLCISSUEAR, Long> getDAO() {
		return imlcissueardao;
	}
}