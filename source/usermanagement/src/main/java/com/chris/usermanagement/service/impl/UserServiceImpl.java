package com.chris.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

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

        return userDAO.save(user);
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
        return users;
    }

    @Override
    public List<User> selectList(UserVO vo) {

        return userDAO.findAll();
    }

    @Override
    public User selectById(Long id) {

        return userDAO.getOne(id);
    }

}

 