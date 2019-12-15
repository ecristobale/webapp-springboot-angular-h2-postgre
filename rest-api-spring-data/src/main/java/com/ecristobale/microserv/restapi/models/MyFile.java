package com.ecristobale.microserv.restapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FILE")
public class MyFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILENAME")
    private String docName;

    @JsonIgnore
    @Column(name = "FILE")
    @Lob
    private byte[] file;
    
    @Column(name = "FILEURI")
    private String fileUri;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ID_ENTITY", updatable = false, nullable = false)
    private MyEntity myEntity;

    public MyFile() {}
	public MyFile(String docName, byte[] file, MyEntity myEntity, String fileUri) {
		this.docName = docName;
		this.file = file;
		this.myEntity = myEntity;
		this.fileUri = fileUri;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFileUri() {
		return fileUri;
	}
	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}
	public MyEntity getMyEntity() {
		return myEntity;
	}
	public void setMyEntity(MyEntity myEntity) {
		this.myEntity = myEntity;
	}
}
