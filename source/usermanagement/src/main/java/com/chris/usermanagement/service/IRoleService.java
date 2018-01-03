package com.chris.usermanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chris.usermanagement.model.Role;
import com.chris.usermanagement.vo.RoleVO;

/**
 * The interface IRoleService
 *
 * @author chris
 * @Date 2018-01-03 15:34
 */
public interface IRoleService {

	/**
	 * 新增一条记录
     * @param vo 对象
     * @return Role
	 */
	Role save(RoleVO vo);

	/**
	 * 根据 ID 删除
     * @param id
     * @return void 
	 */
	void deleteById(Long id);

	/**
	 * 根据实体 修改
     * @param vo 对象
     * @return Role
	 */
	Role updateRole(RoleVO vo);

	/**
	 * 根据 vo条件，查询全部记录
     * @param vo 对象
     * @return List<Role>
	 */
	List<Role> selectList(RoleVO vo);

	/**
	 * 根据 condition 条件，查询全部记录（并翻页）
	 * @param condition 条件
	 * @param page    
	 * @return Page<Role>
	 */
	Page<Role> listPageByCondition(String condition, Pageable page);

	/**
	 * 根据 ID 查询
	 * @param id
	 * @return Role
	 */
	Role selectById(Long id);
}
