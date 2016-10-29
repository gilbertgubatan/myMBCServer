package com.mbc.server.sample;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "MBCMember" database table.
 * 
 */
@Entity
@Table(name="\"MBCMember\"")
@NamedQuery(name="MBCMember.findAll", query="SELECT m FROM MBCMember m")
public class MBCMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="\"FirstName\"")
	private String firstName;

	public MBCMember() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}