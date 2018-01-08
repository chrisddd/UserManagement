package com.chris.usermanagement.controller;

import com.chris.usermanagement.model.User;
import com.chris.usermanagement.service.IUserService;
import com.chris.usermanagement.util.BaseController;
import com.chris.usermanagement.util.Constants;
import com.chris.usermanagement.util.ResultResponse;
import com.chris.usermanagement.util.ValidationNumber;
import com.xiaoleilu.hutool.date.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * The type Login controller.
 *
 * @author did
 * @date 2017年11月24日
 */
@Controller
@RequestMapping("/api/account")
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/valicode", method = RequestMethod.GET)
    public void valicode(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 生成验证码图片
            ValidationNumber.createLogionValidationNumber(request, response);
        } catch (Exception e) {

            // 打出错误信息
//            ERROR.error(LoginController.class, e);
        }

    }
    /**
     * Login result response.
     *
     * @param request  the request
     * @param userCode the user code
     * @param password the password
     * @return the result response
     */
    @ApiOperation(value = "系统", notes = "登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public ResultResponse login(HttpServletRequest request, String userCode, String password) {
//        PssLogFactory.getOperationLog().info("操作人：" + userCode + "--------------操作时间："
//                + DateUtil.formatHttpDate(new Date()) + "登录");
        ResultResponse resultResponse = new ResultResponse();
        userCode = userCode.trim();
        password = password.trim();
        try {
            request.getSession().invalidate();
            if (StringUtils.isBlank(userCode) || StringUtils.isBlank(password)) {
                resultResponse.setResult(false);
                return resultResponse;
            }
            User user = userService.login(userCode, password);
            if (user != null) {
                request.getSession().setAttribute(Constants.LOGIN_SESSION_VALUE, user);
                request.getSession().setAttribute(Constants.LOGIN_SESSION_USERCODE, user.getUserCode());
                resultResponse.setResult(true);
                resultResponse.setData(user);
                return resultResponse;
            } else {
                resultResponse.setResult(false);
                return resultResponse;
            }
        } catch (Exception e) {
//            PssLogFactory.getErrorLog().error(e.getMessage(), e);
            resultResponse.setResult(false);
            return resultResponse;
        }
    }

    /**
     * Logout result response.
     *
     * @param request the request
     * @return the result response
     */
    @ApiOperation(value = "系统", notes = "退出登录")
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
//		PssLogFactory.getOperationLog().info("操作人：" + getLoginUser().getUserCode() + "--------------操作时间："
//				+ DateUtil.formatHttpDate(new Date()) + "退出登录");
//		return process(t -> {
        HttpSession session = request.getSession(true);
        session.removeAttribute(Constants.LOGIN_SESSION_VALUE);
        session.invalidate();
//		});
        return "login";
    }
}

