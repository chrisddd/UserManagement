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

import com.chris.usermanagement.dao.RoleDAO;
import com.chris.usermanagement.model.Role;
import com.chris.usermanagement.service.IRoleService;
import com.chris.usermanagement.util.Constants;
import com.chris.usermanagement.vo.RoleVO;

/**
 * The class RoleServiceImpl
 *
 * @author chris
 * @Date 2018-01-03 15:34
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Role save(RoleVO vo) {
        Role role = new Role();
        role.setRoleName(vo.getRoleName());
        role.setRoleEnname(vo.getRoleEnname());
        role.setRoleType(vo.getRoleType());
        role.setUseable(vo.getUseable());
        role.setCreateUser("admin");
        role.setCreateDate(new Date());
        role.setUpdateUser("admin");
        role.setUpdateDate(new Date());


        return roleDAO.save(role);
    }

    @Override
    public void deleteById(Long id) {

        roleDAO.delete(id);
    }

    @Override
    public Role updateRole(RoleVO vo) {
        Role role = new Role();

        return roleDAO.saveAndFlush(role);
    }

    @Override
    public Page<Role> listPageByCondition(String condition, Pageable page) {
        Page<Role> roles = roleDAO.findAll((root, query, cb) -> {

            List<Predicate> list = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(condition)) {
                list.add(cb.like(root.get("condition").as(String.class), "%" + condition + "%"));
            }
//			list.add(cb.equal(root.get("status").as(String.class), Constants.Status.Active.toString()));
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));

        }, page);
        return roles;
    }

    @Override
    public List<Role> selectList(RoleVO vo) {

        return roleDAO.findAll();
    }

    @Override
    public Role selectById(Long id) {

        return roleDAO.getOne(id);
    }

}

 