package com.chris.usermanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chris.usermanagement.model.User;
import com.chris.usermanagement.vo.UserVO;

/**
 * The interface IUserService
 *
 * @author chris
 * @Date 2018-01-03 14:49
 */
public interface IUserService {

	/**
	 * 新增一条记录
     * @param vo 对象
     * @return User
	 */
	User save(UserVO vo);

	/**
	 * 根据 ID 删除
     * @param id
     * @return void 
	 */
	void deleteById(Long id);

	/**
	 * 根据实体 修改
     * @param vo 对象
     * @return User
	 */
	User updateUser(UserVO vo);

	/**
	 * 根据 vo条件，查询全部记录
     * @param vo 对象
     * @return List<User>
	 */
	List<User> selectList(UserVO vo);

	/**
	 * 根据 condition 条件，查询全部记录（并翻页）
	 * @param condition 条件
	 * @param page    
	 * @return Page<User>
	 */
	Page<User> listPageByCondition(String condition, Pageable page);

	/**
	 * 根据 ID 查询
	 * @param id
	 * @return User
	 */
	User selectById(Long id);
}
