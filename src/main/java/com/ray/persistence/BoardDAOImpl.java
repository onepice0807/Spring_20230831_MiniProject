package com.ray.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ray.vodto.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession ses;
	
	private String ns = "com.ray.mappers.boardMapper";

	@Override
	public List<Board> selectAllBoard() {
		
		
		return ses.selectList(ns + ".getAllBoard");
	}

}
