package com.chris.usermanagement.controller;

import java.util.List;

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

import com.chris.usermanagement.model.User;
import com.chris.usermanagement.service.IUserService;
import com.chris.usermanagement.util.BaseController;
import com.chris.usermanagement.util.ResultResponse;
import com.chris.usermanagement.vo.UserVO;

import io.swagger.annotations.ApiOperation;

/**
 * The class UserController 控制器
 *
 * @author chris
 * @Date 2018-01-03 14:49
 */
@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {

    protected final Log logger = LogFactory.getLog(getClass());

    private String PREFIX = "/user/";

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "跳转到user首页", notes = "跳转到user首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return PREFIX + "user";
    }

    @ApiOperation(value = "跳转到添加", notes = "跳转到添加")
    @RequestMapping(value = "/goto_add", method = RequestMethod.GET)
    public String userAdd() {
        return PREFIX + "user_add";
    }

    @ApiOperation(value = "跳转到详情", notes = "跳转到详情")
    @RequestMapping(value = "/goto_detail", method = RequestMethod.GET)
    public String userDetail(Long id, Model model) {
        model.addAttribute("user", userService.selectById(id));
        return PREFIX + "user_detail";
    }

    @ApiOperation(value = "跳转到修改", notes = "跳转到修改")
    @RequestMapping(value = "/goto_update", method = RequestMethod.GET)
    public String userUpdate(Long id, Model model) {
        model.addAttribute("user", userService.selectById(id));
        return PREFIX + "user_edit";
    }

    @ApiOperation(value = "User新增", notes = "新增User 记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse add(UserVO vo) {

        return process(t -> {
            User user = userService.save(vo);
            t.setData(user);
        });
    }

    @ApiOperation(value = "User删除", notes = "根据User id删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse delete(Long id) {

        return process(t -> {
            userService.deleteById(id);
            t.setData("");
        });
    }

    @ApiOperation(value = "User修改", notes = "根据User id修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse update(Long id, UserVO vo) {

        return process(t -> {
            User user = userService.updateUser(vo);
            t.setData(user);
        });
    }

//@ApiOperation(value = "User列表", notes = "获取User列表")
//@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
//@ResponseBody
//public ResultResponse list(UserVO vo) {
//
//		return process(t -> {
//		List<User> users = userService.selectList(vo);
//		t.setData(users);
//		});
//
//		}

    @ApiOperation(value = "User列表", notes = "获取User列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model, UserVO vo) {

        List<User> users = userService.selectList(vo);
        model.addAttribute("users", users);
        return PREFIX + "user_data";
    }

    @ApiOperation(value = "User分页列表", notes = "获取User分页列表")
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse list(String condition, @RequestParam(required = true) Integer pageNumber, Integer pageSize) {

        return process(t -> {
            int page = pageSize != null ? pageSize : 10;
            Page<User> users = userService.listPageByCondition(condition, new PageRequest(pageNumber, page));
            t.setData(users);
        });

    }

    @ApiOperation(value = "User详情", notes = "根据User id获取详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultResponse detail(Long id) {
        return process(t -> {
            User user = userService.selectById(id);
            t.setData(user);
        });
    }

    @ApiOperation(value = "个人信息", notes = "跳转到个人信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String userInfo() {
        return PREFIX + "user_info";
    }
}

 