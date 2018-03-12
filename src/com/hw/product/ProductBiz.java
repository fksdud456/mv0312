package com.hw.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hw.frame.Biz;
import com.hw.frame.Dao;
import com.hw.vo.Product;
@Service("productBiz")
public class ProductBiz implements Biz<Product, Integer> {

	@Resource(name="productDao")
	Dao<Product, Integer> dao;
	
	public ProductBiz( ) {
		dao = new ProductDao();
	}
	
	@Transactional
	@Override
	public void register(Product p) {
		dao.insert(p);
	}

	@Transactional
	@Override
	public void remove(Integer s) {
		dao.delete(s);
	}

	@Transactional
	@Override
	public void modify(Product p) {
		dao.update(p);
	}

	@Transactional
	@Override
	public Product get(Integer s) {
		return dao.select(s);
	}

	@Transactional
	@Override
	public List<Product> get() {
		return dao.select();
	}
}
