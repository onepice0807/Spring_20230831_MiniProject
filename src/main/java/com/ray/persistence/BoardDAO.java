package com.ray.persistence;

import java.util.List;

import com.ray.vodto.Board;

public interface BoardDAO {
	List<Board> selectAllBoard();
}
