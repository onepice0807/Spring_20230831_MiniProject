package com.ray.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository  // 아래의 클래스가 DAO객체임을 명시
public class MemberDAOImp implements MemberDAO {

	@Inject
	private SqlSession ses; // SqlSessionTemplate 객체 주입
	private static String ns = "com.ray.mappers.memberMapper";
	
	@Override
	public String getDate() {
		//Connection
		
		String query = ns + ".curDate";
		
		return ses.selectOne(query);
	}
}
