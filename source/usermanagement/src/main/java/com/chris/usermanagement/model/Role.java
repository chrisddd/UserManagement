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

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The class role model
 *
 * @author chris
 * @Date 2018-01-03 17:58
 */
@Data
@Entity
@Table(name = "role")
@DynamicInsert
@DynamicUpdate
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**
     * 归属机构
     */
    @Column(name = "office_id")
    private String officeId;


    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;


    /**
     * 英文名称
     */
    @Column(name = "role_enname")
    private String roleEnname;


    /**
     * 角色类型
     */
    @Column(name = "role_type")
    private String roleType;


    /**
     * 是否可用
     */
    @Column(name = "useable")
    private String useable;


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

}
