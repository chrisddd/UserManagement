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
 * The class role model
 *
 * @author chris
 * @Date 2018-01-03 15:52
 */
@Entity
@Table(name = "role")
@DynamicInsert
@DynamicUpdate
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**
     * officeId
     */
    @Column(name = "office_id")
    private String officeId;


    /**
     * roleName
     */
    @Column(name = "role_name")
    private String roleName;


    /**
     * roleEnname
     */
    @Column(name = "role_enname")
    private String roleEnname;


    /**
     * roleType
     */
    @Column(name = "role_type")
    private String roleType;


    /**
     * useable
     */
    @Column(name = "useable")
    private String useable;


    /**
     * createUser
     */
    @Column(name = "create_user")
    private String createUser;


    /**
     * createDate
     */
    @Column(name = "create_date")
    private Date createDate;


    /**
     * updateUser
     */
    @Column(name = "update_user")
    private String updateUser;


    /**
     * updateDate
     */
    @Column(name = "update_date")
    private Date updateDate;


    /**
     * remarks
     */
    @Column(name = "remarks")
    private String remarks;


    /**
     * delFlag
     */
    @Column(name = "del_flag")
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleEnname() {
        return roleEnname;
    }

    public void setRoleEnname(String roleEnname) {
        this.roleEnname = roleEnname;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
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
