package com.chris.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chris.usermanagement.model.Role;

/**
 * The interface RoleDAO.
 *
 * @author chris
 * @Date 2018-01-03 15:34
 */
public interface RoleDAO extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {


}
