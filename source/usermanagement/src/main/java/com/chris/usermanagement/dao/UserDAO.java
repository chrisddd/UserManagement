package com.chris.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chris.usermanagement.model.User;

/**
 * The interface UserDAO.
 *
 * @author chris
 * @Date 2018-01-03 14:49
 */
public interface UserDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUserCodeAndPasswordAndDelFlag(String userCode, String password, String delFlag);
}
