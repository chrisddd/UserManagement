package com.chris.usermanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The class user model
 * 
 *
 * @author chris
 * @Date 2018-01-03 18:22
 */
@Data
@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
public class User implements Serializable  {

    private static final long serialVersionUID = 1L;

	/**
	 * 编号
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
	 * 姓名
	 */
	@Column(name = "user_name")
	private String userName;


	/**
	 * 密码
	 */
	@Column(name = "password")
    @JsonIgnore
	private String password;


	/**
	 * 归属公司
	 */
	@Column(name = "company_id")
	private Long companyId;


	/**
	 * 归属部门
	 */
	@Column(name = "office_id")
	private Long officeId;


	/**
	 * 工号
	 */
	@Column(name = "number")
	private String number;


	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;


	/**
	 * 手机
	 */
	@Column(name = "mobile")
	private String mobile;


	/**
	 * 用户类型
	 */
	@Column(name = "user_type")
	private String userType;


	/**
	 * 用户头像
	 */
	@Column(name = "photo")
	private String photo;


	/**
	 * 最后登陆IP
	 */
	@Column(name = "login_ip")
	private String loginIp;


	/**
	 * 最后登陆时间
	 */
	@Column(name = "login_date")
	private Date loginDate;


	/**
	 * 创建者
	 */
	@Column(name = "create_user")
	private String createUser;


	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	private Date createDate;


	/**
	 * 更新者
	 */
	@Column(name = "update_user")
	private String updateUser;


	/**
	 * 更新时间
	 */
	@Column(name = "update_date")
	private Date updateDate;


	/**
	 * 备注信息
	 */
	@Column(name = "remarks")
	private String remarks;


	/**
	 * 删除标记
	 */
	@Column(name = "del_flag")
	private String delFlag;

	@Transient
	List<Role> roles = Lists.newArrayList();


}
