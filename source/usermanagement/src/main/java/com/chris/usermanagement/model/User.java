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
 * The class user model
 *
 * @author chris
 * @Date 2018-01-03 18:02
 */
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
	private String password;


	/**
	 * 归属公司
	 */
	@Column(name = "company_id")
	private Integer companyId;


	/**
	 * 归属部门
	 */
	@Column(name = "office_id")
	private Integer officeId;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
