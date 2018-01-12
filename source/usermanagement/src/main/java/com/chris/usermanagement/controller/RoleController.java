package com.chris.usermanagement.controller;

import java.util.List;

import com.chris.usermanagement.util.Permission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import com.chris.usermanagement.model.Role;
import com.chris.usermanagement.service.IRoleService;
import com.chris.usermanagement.util.BaseController;
import com.chris.usermanagement.util.ResultResponse;
import com.chris.usermanagement.vo.RoleVO;

import io.swagger.annotations.ApiOperation;

/**
 * The class RoleController 控制器
 *
 * @author chris
 * @Date 2018-01-03 15:34
 */
@Controller
@RequestMapping("/api/role")
public class RoleController extends BaseController {

protected final Log logger = LogFactory.getLog(getClass());

private String PREFIX = "role/";

@Autowired
private IRoleService roleService;

@ApiOperation(value = "跳转到role首页", notes = "跳转到role首页")
@RequestMapping(value = "/index", method = RequestMethod.GET)
@Permission(roles = { "admin" })
public String index() {
		return PREFIX + "role";
		}

@ApiOperation(value = "跳转到添加", notes = "跳转到添加")
@RequestMapping(value = "/goto_add", method = RequestMethod.GET)
@Permission(roles = { "admin" })
public String roleAdd() {
		return PREFIX + "role_add";
		}

@ApiOperation(value = "跳转到详情", notes = "跳转到详情")
@RequestMapping(value = "/goto_detail", method = RequestMethod.GET)
@Permission(roles = { "admin" })
public String roleDetail(Long id, Model model) {
		model.addAttribute("role", roleService.selectById(id));
		return PREFIX + "role_detail";
		}

@ApiOperation(value = "跳转到修改", notes = "跳转到修改")
@RequestMapping(value = "/goto_update", method = RequestMethod.GET)
@Permission(roles = { "admin" })
public String roleUpdate(Long id, Model model) {
		model.addAttribute("role", roleService.selectById(id));
		return PREFIX + "role_edit";
		}

@ApiOperation(value = "Role新增", notes = "新增Role 记录")
@RequestMapping(value = "/add", method = RequestMethod.POST)
@ResponseBody
@Permission(roles = { "admin" })
public ResultResponse add(RoleVO vo) {

		return process(t -> {
		Role role = roleService.save(vo);
		t.setData(role);
		});
		}

@ApiOperation(value = "Role删除", notes = "根据Role id删除")
@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
@Permission(roles = { "admin" })
public ResultResponse delete(Long id) {

		return process(t -> {
		roleService.deleteById(id);
		t.setData("");
		});
		}

@ApiOperation(value = "Role修改", notes = "根据Role id修改")
@RequestMapping(value = "/update", method = RequestMethod.POST)
@ResponseBody
@Permission(roles = { "admin" })
public ResultResponse update(Long id, RoleVO vo) {

		return process(t -> {
		Role role = roleService.updateRole(vo);
		t.setData(role);
		});
		}

//@ApiOperation(value = "Role列表", notes = "获取Role列表")
//@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
//@ResponseBody
//@Permission(roles = { "admin" })
//public ResultResponse list(RoleVO vo) {
//
//		return process(t -> {
//		List<Role> roles = roleService.selectList(vo);
//		t.setData(roles);
//		});
//
//		}

@ApiOperation(value = "Role列表", notes = "获取Role列表")
@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
@Permission(roles = { "admin" })
public String list(Model model, RoleVO vo) {

		List<Role> roles = roleService.selectList(vo);
		model.addAttribute("roles", roles);
		return PREFIX + "role_data";
		}

@ApiOperation(value = "Role分页列表", notes = "获取Role分页列表")
@RequestMapping(value = "/page" , method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
@Permission(roles = { "admin" })
public ResultResponse list(String condition, @RequestParam(required = true) Integer pageNumber, Integer pageSize) {

		return process(t -> {
		int page = pageSize != null ? pageSize : 10;
		Page<Role> roles = roleService.listPageByCondition(condition, new PageRequest(pageNumber, page));
		t.setData(roles);
		});

		}

@ApiOperation(value = "Role详情", notes = "根据Role id获取详情")
@RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
@Permission(roles = { "admin" })
public ResultResponse detail(Long id) {
		return process(t -> {
		Role role = roleService.selectById(id);
		t.setData(role);
		});
		}
		}

 