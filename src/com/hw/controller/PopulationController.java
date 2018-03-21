package com.hw.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hw.frame.Biz;
import com.hw.vo.Population;

@Controller
public class PopulationController {
	@Resource(name = "populationBiz")
	Biz<Population, String> biz;

	@RequestMapping("/populationlist.do")
	@ResponseBody
	public void list(HttpServletResponse res) throws Exception {
		// Database에서 데이터를 가지고 온다.
		List<Population> list = null;
		
		list = biz.get();
		
		JSONArray ja = new JSONArray();
		
		for (Population d : list) {
			JSONObject data = new JSONObject();
			data.put("name", d.getDong());
			data.put("y", d.getOne());
			data.put("Z", 55);
			ja.add(data);
		}
		
		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		out.print(ja.toJSONString());
		out.close();
	}

	@RequestMapping("/populationdetail.do")
	public String detail(Model m, String id) {
		System.out.println("populationdetail" + id);

		m.addAttribute("population", biz.get(id));
		m.addAttribute("center", "population/detail");
		return "main";
	}
}
