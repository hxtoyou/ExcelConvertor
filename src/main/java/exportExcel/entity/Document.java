/*
 * Copyright (C) 2010-2020 CCRISE.
 */
package exportExcel.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import exportExcel.ORM.DAO.IDEntity;
import exportExcel.utils.JsonTimeDeserializer;
import exportExcel.utils.JsonTimeSerializer;

/**
 * Document。
 * 
 * @author Xiao He(hxtoyou@163.com)
 */
@Entity
@Table(name = "S_document")
public class Document extends IDEntity {
	// 文档名称
	private String documentName;

	// 查询关键字
	private String keyWord;

	// 创建时间
	private Timestamp createTime;

	// 最后更新时间
	private Timestamp lastModifyTime;

	// 附件
	private UploadedFile attachment;
	/**
	 * 文件夹id
	 */
	private Long folderId;

	/**
	 * 文档的保密层级 3:公开(所有人可见) 2:保护(同组织内可见) 1:私有(仅自己可见)
	 */
	private Integer securityLevel;

	public Integer getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	@ManyToOne
	public UploadedFile getAttachment() {
		return attachment;
	}

	@Column(updatable = false)
	@JsonSerialize(using = JsonTimeSerializer.class)
	@JsonDeserialize(using = JsonTimeDeserializer.class)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getDocumentName() {
		return documentName;
	}

	public String getKeyWord() {
		return keyWord;
	}

	@Column(updatable = true)
	@JsonSerialize(using = JsonTimeSerializer.class)
	@JsonDeserialize(using = JsonTimeDeserializer.class)
	public Timestamp getLastModifyTime() {
		return lastModifyTime;
	}

	public void setAttachment(UploadedFile attachment) {
		this.attachment = attachment;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}