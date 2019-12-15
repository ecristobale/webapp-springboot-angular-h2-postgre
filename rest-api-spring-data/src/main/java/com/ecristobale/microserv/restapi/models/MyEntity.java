package com.ecristobale.microserv.restapi.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ENTITY")
public class MyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToOne(mappedBy = "myEntity", cascade = CascadeType.ALL)
	private MyFile myFile;
	
	public MyEntity() {}
	public MyEntity(String name, String description, MyFile myFile) {
		this.name = name;
		this.description = description;
		this.myFile = myFile;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MyFile getMyFile() {
		return myFile;
	}
	public void setMyFile(MyFile myFile) {
		this.myFile = myFile;
	}
	@Override
	public String toString() {
		return "MyEntity [id=" + id + ", name=" + name 
				+ ", description=" + description + "]";
	}
}
