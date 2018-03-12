package com.hw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hw.frame.Biz;
import com.hw.vo.User;

@Controller
public class UserController {
		
	@Resource(name="userBiz")
	Biz<User,String> biz;
	
	@RequestMapping("/userlist.do")
	public String list(Model m) {
		System.out.println("userlist" + m);
		
		List<User> list = biz.get();
		m.addAttribute("userlist", list);
		m.addAttribute("center", "user/list");
		return "main";
	}
	
	@RequestMapping("/useradd.do")
	public String add(Model m) {
		System.out.println("useradd" + m);
		
		m.addAttribute("center", "user/add");
		return "main";
	}
	
	@RequestMapping("/useraddimpl.do")
	public String addimpl(Model m, User u) {
		System.out.println("useraddimpl:" + m);
		
		biz.register(u);
		m.addAttribute("center", "user/addok");
		return "main";
	}
	
	@RequestMapping("/userdetail.do")
	public String detail(Model m, String id) {
		System.out.println("userdetail" + id);
		
		m.addAttribute("user", biz.get(id));
		m.addAttribute("center", "user/detail");
		return "main";
	}
	
	@RequestMapping("/userupdate.do")
	public String update(Model m, User u) {
		System.out.println("userupdate" + m);
		biz.modify(u);
		m.addAttribute("center", "user/detail");
		return "main";
	}
	
	@RequestMapping("/userdelete.do")
	public String delete(Model m, String id) {
		System.out.println("userdelete" + m);
		
		biz.remove(id);
		m.addAttribute("center", "user/deleteok");
		return "main";
	}
}



