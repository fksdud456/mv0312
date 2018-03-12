package com.hw.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hw.frame.Dao;
import com.hw.mapper.ProductMapper;
import com.hw.vo.Product;
@Repository("productDao")
public class ProductDao implements Dao<Product, Integer> {
	@Autowired
	ProductMapper mapper;
	
	@Override
	public void insert(Product t) {
		mapper.insert(t);
	}

	@Override
	public void delete(Integer s) {
		mapper.delete(s);
	}

	@Override
	public void update(Product t) {
		mapper.update(t);
	}

	@Override
	public Product select(Integer s) {
		return mapper.select(s);
	}

	@Override
	public List<Product> select() {
		return mapper.selectall();
	}

}
