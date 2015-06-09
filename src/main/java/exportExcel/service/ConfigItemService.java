/*
 * Copyright (C) 2012 CCRISE.
 */
package exportExcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exportExcel.ORM.DAO.HibernateDAO;
import exportExcel.ORM.service.HibernateDataServiceImpl;
import exportExcel.access.ConfigItemDAO;
import exportExcel.entity.ConfigItem;

/**
 * ExcelTemplate Service。
 * 
 * @author Page(hxtoyou@gmail.com)
 */
@Service
public class ConfigItemService extends HibernateDataServiceImpl<ConfigItem, Long> {
	@Autowired
	private ConfigItemDAO configItemDAO;

	@Override
	public HibernateDAO<ConfigItem, Long> getDAO() {
		return configItemDAO;
	}
}