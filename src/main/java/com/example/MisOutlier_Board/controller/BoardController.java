package com.example.MisOutlier_Board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MisOutlier_Board.domain.BoardDTO;
import com.example.MisOutlier_Board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	GetMapping()
//	1. 서버로부터 정보를 조회하기 위해 설계된 메소드 = select
//	2. 요청을 전송할 때 필요한 데이터를 body에 담지 않고, 쿼리 스트링을 통해 전송 (url 끝에 ?와 함께 값을 요청하여, 조회할 데이터를 요청)
	@GetMapping("/board/list.do")
	public String BoardList(Model model) {
		
		List<BoardDTO> list = boardService.getBoardList();
		model.addAttribute("list", list);
		
		return "Board/BoardList";
	}
	
	@GetMapping("/board/detail.do")
	public String BoardDetail(Model model, @RequestParam long idx) {
		
		System.out.println("Controller ok");
		BoardDTO BoardDetail = boardService.getBoardDetail(idx);
		model.addAttribute("detail", BoardDetail);
		
		System.out.println("Model OK");
	
		return "Board/BoardDetail";
	}
	
	
//	PostMapping()
//	1. 리소스를 생성/변경하기 위해 설계된 메소드 = update
//	2. 데이터를 body에 담아서 전송
	

}
