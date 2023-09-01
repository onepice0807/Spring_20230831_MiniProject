package com.ray.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ray.etc.uploadFileProcess;
import com.ray.service.board.BoardService;
import com.ray.vodto.Board;
import com.ray.vodto.UploadedFile;

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

	/**
	 * 
	 * @MethodName : showWriteBoard
	 * @author : ray
	 * @param :
	 * @throws IOException
	 * @returnValue :
	 * @description :
	 * @date : 2023. 9. 1.
	 */

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public void uploadFile(MultipartFile uploadFile, HttpServletRequest req) {
		// uploadFile.getBytes() : 파일의 2진 데이터
		logger.info("파일을 업로드 했다");
		System.out.println("파일의 오리지날 이름 : " + uploadFile.getOriginalFilename());
		System.out.println("파일의 사이즈 : " + uploadFile.getSize());
		System.out.println("파일의 contentType : " + uploadFile.getContentType());

		uploadFile.getOriginalFilename();

		String realPath = req.getSession().getServletContext().getRealPath("resources/uploads"); // java파일이랑 webapp 까지는
		System.out.println(realPath);
		try {
			UploadedFile uf = uploadFileProcess.fileUpload(uploadFile.getOriginalFilename(), uploadFile.getSize(),
					uploadFile.getContentType(), uploadFile.getBytes(), realPath);
			
			
			
			// 여러개의 파일 업로드 한꺼번에 할 경우 고민해보기
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 경로가 같아서 경로를 이렇게
			// 지정한다.

	}
}
