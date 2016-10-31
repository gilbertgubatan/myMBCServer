package com.mbc.server.jpa.beans.login;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "RoleAccess" database table.
 * 
 */
@Entity
@Table(name="\"RoleAccess\"")
@NamedQuery(name="RoleAccess.findAll", query="SELECT r FROM RoleAccess r")
public class RoleAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"AccessName\"")
	private String accessName;

	@Column(name="\"RoleId\"")
	private Integer roleId;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumns({@JoinColumn(name="\"RoleId\"", insertable=false, updatable=false)
		})
	private Role role;

	public RoleAccess() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessName() {
		return this.accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}