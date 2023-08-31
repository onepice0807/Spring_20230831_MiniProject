package com.ray.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ray.persistence.BoardDAO;
import com.ray.vodto.Board;

@Service // 아래의 객체가 Service 객체임을 명시
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bDao;

	@Override
	public List<Board> getEntireBoard() {
		
		List<Board> lst = bDao.selectAllBoard();
		
		return lst;
	}

}
