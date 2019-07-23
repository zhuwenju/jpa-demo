package com.frame.base.controller.admin;

import com.frame.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController extends BaseController {
	@RequestMapping(value ={"/admin/","/admin/index"})
	public String index(){
		
		return "admin/index";
	}
	
	@RequestMapping(value = {"/admin/welcome"})
	public String welcome(){
		
		return "admin/welcome";
	}
}
