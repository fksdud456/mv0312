package com.hw.population;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hw.frame.Biz;
import com.hw.frame.Dao;
import com.hw.vo.Population;
@Service("populationBiz")
public class PopulationBiz implements Biz<Population, String> {
	
	@Resource(name = "populationDao")
	Dao<Population, String> dao;

	public PopulationBiz() {
		dao = new PopulationDao();
	}

	@Transactional
	@Override
	public void register(Population t) {

	}

	@Transactional
	@Override
	public void remove(String s) {

	}

	@Transactional
	@Override
	public void modify(Population t) {

	}

	@Transactional
	@Override
	public Population get(String s) {
		return dao.select(s);
	}

	@Transactional
	@Override
	public List<Population> get() {
		return dao.select();
	}

}
