/*
 * Copyright (C) 2010-2020 CCRISE.
 */
package exportExcel.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import exportExcel.ORM.DAO.IDEntity;
import exportExcel.utils.JsonTimeDeserializer;
import exportExcel.utils.JsonTimeSerializer;

/**
 * 上传附件产生的文件记录
 */
@Entity
@Table(name = "S_ploaded_files")
public class UploadedFile extends IDEntity {

	private String simpleName;
	private String filePath;
	private String pdfPath;
	private String swfPath;
	private Timestamp addTime;

	@JsonSerialize(using = JsonTimeSerializer.class)
	@JsonDeserialize(using = JsonTimeDeserializer.class)
	public Timestamp getAddTime() {
		return addTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public String getSwfPath() {
		return swfPath;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}

}