package com.mbc.server.jpa.beans.login;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the "Role" database table.
 * 
 */
@Entity
@Table(name="\"Role\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"RoleName\"")
	private String roleName;

	//bi-directional many-to-one association to RoleAccess
	@OneToMany(mappedBy="role")
	private List<RoleAccess> roleAccesses;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="role")
	private List<User> users;

	public Role() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RoleAccess> getRoleAccesses() {
		return this.roleAccesses;
	}

	public void setRoleAccesses(List<RoleAccess> roleAccesses) {
		this.roleAccesses = roleAccesses;
	}

	public RoleAccess addRoleAccess(RoleAccess roleAccess) {
		getRoleAccesses().add(roleAccess);
		roleAccess.setRole(this);

		return roleAccess;
	}

	public RoleAccess removeRoleAccess(RoleAccess roleAccess) {
		getRoleAccesses().remove(roleAccess);
		roleAccess.setRole(null);

		return roleAccess;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}