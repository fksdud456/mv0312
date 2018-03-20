package com.hw.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.hw.frame.Biz;
import com.hw.vo.Product;
import com.hw.vo.User;

@Controller
public class ProductController {
	@Resource(name="productBiz")
	Biz<Product, Integer> biz;
	
	@RequestMapping("/productlist.do")
	public String list(Model m) {
		System.out.println("productlist" + m);
		
		List<Product> list = biz.get();
		m.addAttribute("productlist", list);
		m.addAttribute("center", "product/list");
		return "main";
	}
	
	@RequestMapping("/productadd.do")
	public String add(Model m) {
		System.out.println("productadd" + m);
		
		m.addAttribute("center", "product/add");
		return "main";
	}
	
	@RequestMapping("/productaddimpl.do")
	public String addimpl(Model m,Product p) {
		System.out.println("productaddimpl" + p);
		
		// C:\\spring\\mv\\web\\img
		MultipartFile mf = p.getMf();
		String imgName = mf.getOriginalFilename();
		p.setImgname(imgName);
		updateImage(mf, imgName);
		
		biz.register(p);
		
		m.addAttribute("imgname",imgName);
		m.addAttribute("center", "product/addok");
		return "main";
	}
	
	@RequestMapping("/productdetail.do")
	public String productdetail(Model m, int id) {
		System.out.println("productdetail" + id);
		
		m.addAttribute("product", biz.get(id));
		m.addAttribute("center", "product/detail");
		return "main";
	}

	@RequestMapping("/productupdate.do")
	public String update(Model m, Product p) {
		System.out.println("productupdate" + m);

		MultipartFile mf = p.getMf();
		if (!mf.isEmpty()) {
			String imgName = mf.getOriginalFilename();
			p.setImgname(imgName);
			updateImage(mf, imgName);
		}

		biz.modify(p);
		m.addAttribute("center", "product/addok");
		return "main";
	}
	
	@RequestMapping("/productdelete.do")
	public String delete(Model m, int id) {
		System.out.println("productdelete" + m);

		biz.remove(id);
		m.addAttribute("center", "product/deleteok");
		return "main";
	}
	
	public void updateImage(MultipartFile mf, String imgName) {
		byte[] data = null;
		FileOutputStream fo = null;
		try {
			data = mf.getBytes();
			fo = 
			new FileOutputStream("C:\\spring\\mv\\web\\img\\"+imgName);
			fo.write(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}




