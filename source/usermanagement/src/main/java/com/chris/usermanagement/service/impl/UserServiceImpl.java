package com.chris.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import com.chris.usermanagement.dao.RoleDAO;
import com.chris.usermanagement.dao.UserRoleMapDAO;
import com.chris.usermanagement.model.Role;
import com.chris.usermanagement.model.UserRoleMap;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chris.usermanagement.dao.UserDAO;
import com.chris.usermanagement.model.User;
import com.chris.usermanagement.service.IUserService;
import com.chris.usermanagement.util.Constants;
import com.chris.usermanagement.vo.UserVO;

/**
 * The class UserServiceImpl
 *
 * @author chris
 * @Date 2018-01-03 14:49
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleMapDAO userRoleMapDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public User save(UserVO vo) {
        User user = new User();
        user.setUserCode(vo.getUserCode());
        user.setUserName(vo.getUserName());
        user.setPassword(vo.getPassword());
        user.setCreateUser("admin");
        user.setCreateDate(new Date());
        user.setUpdateUser("admin");
        user.setUpdateDate(new Date());

        List<Long> roleIds = vo.getRoleIds();
        List<Role> roles = roleDAO.findByIdIn(roleIds);
        user.setRoles(roles);

        roleIds.forEach(roleId -> {
                    UserRoleMap userRoleMap = new UserRoleMap();
                    userRoleMap.setUserCode(vo.getUserCode());
                    userRoleMap.setRoleId(roleId);
                    userRoleMapDAO.save(userRoleMap);
                }
        );

        user = userDAO.save(user);
        return user;
    }

    @Override
    public void deleteById(Long id) {

        userDAO.delete(id);
    }

    @Override
    public User updateUser(UserVO vo) {
        User user = new User();

        return userDAO.saveAndFlush(user);
    }

    @Override
    public Page<User> listPageByCondition(String condition, Pageable page) {
        Page<User> users = userDAO.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(condition)) {
                list.add(cb.like(root.get("condition").as(String.class), "%" + condition + "%"));
            }
//			list.add(cb.equal(root.get("status").as(String.class), Constants.Status.Active.toString()));
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));

        }, page);
        users.forEach(user -> {
            user.setRoles(getRoles(user.getUserCode()));
        });
        return users;
    }

    @Override
    public List<User> selectList(UserVO vo) {
        List<User> users = userDAO.findAll();
        users.forEach(user -> {
            user.setRoles(getRoles(user.getUserCode()));
        });

        return users;
    }

    @Override
    public User selectById(Long id) {
        User user = userDAO.getOne(id);
        user.setRoles(getRoles(user.getUserCode()));
        return user;
    }

    @Override
    public User login(String userCode, String password) {
        String delFlag="0";
        User user = userDAO.findByUserCodeAndPasswordAndDelFlag(userCode,password,delFlag);
        if (null != user) {
            user.setRoles(getRoles(user.getUserCode()));
            user.setLoginDate(new Date());
        }
        return user;
    }

    private List<Role> getRoles(String userCode) {
        List<UserRoleMap> userRoleMaps = userRoleMapDAO.findByUserCode(userCode);
        List<Long> roleIds = userRoleMaps.stream().map(UserRoleMap::getRoleId).collect(Collectors.toList());
        List<Role> roles = roleDAO.findByIdIn(roleIds);
        return roles;
    }
}

 