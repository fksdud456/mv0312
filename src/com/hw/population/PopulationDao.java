package com.hw.population;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hw.frame.Dao;
import com.hw.mapper.PopulationMapper;
import com.hw.vo.Population;
@Repository("populationDao")
public class PopulationDao implements Dao<Population, String> {
	@Autowired
	PopulationMapper mapper;
	
	@Override
	public void insert(Population t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Population t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Population select(String s) {
		return mapper.select(s);
	}

	@Override
	public List<Population> select() {
		// TODO Auto-generated method stub
		return mapper.selectall();
	}
}
