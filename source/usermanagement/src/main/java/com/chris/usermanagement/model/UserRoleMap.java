package com.chris.usermanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The class user_role_map model
 * 
 *
 * @author chris
 * @Date 2018-01-04 14:10
 */
@Entity
@Table(name = "user_role_map")
@DynamicInsert
@DynamicUpdate
public class UserRoleMap implements Serializable  {

    private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	/**
	 * 登录名
	 */
	@Column(name = "user_code")
	private String userCode;


	/**
	 * 角色id
	 */
	@Column(name = "role_id")
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
