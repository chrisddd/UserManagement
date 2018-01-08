package com.chris.usermanagement.controller;

import com.chris.usermanagement.util.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController{

	@GetMapping("")
	@ApiOperation(value = "首页", notes = "返回首页")
	public String indexEmpty() {
		return "index";
	}

	@GetMapping("/")
	@ApiOperation(value = "首页", notes = "返回首页")
	public String index2() {
		return "index";
	}

	@GetMapping("/index")
	@ApiOperation(value = "首页", notes = "返回首页")
	public String index3() {
		return "index";
	}

	@GetMapping("/user")
	@ApiOperation(value = "首页", notes = "返回首页")
	public String user() {
		return "user";
	}

	@GetMapping("/role")
	@ApiOperation(value = "首页", notes = "返回首页")
	public String role() {
		return "role";
	}
}
