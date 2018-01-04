package com.chris.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chris.usermanagement.model.UserRoleMap;

import java.util.List;

/**
 * The interface UserRoleMapDAO.
 *
 * @author chris
 * @Date 2018-01-04 14:10
 */
public interface UserRoleMapDAO extends JpaRepository<UserRoleMap, Long>, JpaSpecificationExecutor<UserRoleMap> {

    List<UserRoleMap> findByUserCode(String userCode);
}
