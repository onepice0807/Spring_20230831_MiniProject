package com.ray.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ray.service.board.BoardService;
import com.ray.vodto.Board;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService bService; // BoardService 객체 주입
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("listAll")
	public void listAll(Model model) {
		logger.info("게시판 전체 글 조회");
		// service -> dao -> db -> service
		List<Board> lst = bService.getEntireBoard();
		
		model.addAttribute("boardList", lst);	
	}
	
	
	
	
	@RequestMapping("writeBoard")
	public void showWriteBoard() {
		
	}
	
	@RequestMapping(value= "uploadFile", method= RequestMethod.POST)
	public void uploadFile(MultipartFile uploadFile) { 
		logger.info("파일을 업로드 했다"); 
		System.out.println("파일의 오리지날 이름 : " + uploadFile.getOriginalFilename());
		System.out.println("파일의 사이즈 : " + uploadFile.getSize());
		System.out.println("파일의 contentType : " + uploadFile.getContentType());
		
	}
}
