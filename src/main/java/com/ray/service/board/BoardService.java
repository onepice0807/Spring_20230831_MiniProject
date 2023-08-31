package com.ray.service.board;

import java.util.List;

import com.ray.vodto.Board;

public interface BoardService {
	// 게시글 전체 조회
	List<Board> getEntireBoard();
}
