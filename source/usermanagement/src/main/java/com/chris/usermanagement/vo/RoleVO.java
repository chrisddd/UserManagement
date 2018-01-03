package com.chris.usermanagement.vo;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Role vo.
 *
 * @author did
 * @date 2017年12月27日
 */
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 900658938189087722L;

    private Long id;


    /**
     * officeId
     */
    private String officeId;


    /**
     * roleName
     */
    private String roleName;


    /**
     * roleEnname
     */
    @Column(name = "role_enname")
    private String roleEnname;


    /**
     * roleType
     */
    private String roleType;


    /**
     * useable
     */
    private String useable;


    /**
     * createUser
     */
    private String createUser;


    /**
     * createDate
     */
    private Date createDate;


    /**
     * updateUser
     */
    private String updateUser;


    /**
     * updateDate
     */
    private Date updateDate;


    /**
     * remarks
     */
    private String remarks;


    /**
     * delFlag
     */
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
