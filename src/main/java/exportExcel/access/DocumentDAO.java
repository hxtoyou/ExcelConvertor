/*
 * Copyright (C) 2010-2020 CCRISE.
 */
package exportExcel.access;

import org.springframework.stereotype.Repository;

import exportExcel.ORM.DAO.HibernateDAOImpl;
import exportExcel.entity.Document;

/**
 * Document DAO。
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
@Repository
public class DocumentDAO extends HibernateDAOImpl<Document, Long> {
}