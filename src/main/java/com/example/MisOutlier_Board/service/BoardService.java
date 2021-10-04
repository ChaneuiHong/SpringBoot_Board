package com.example.MisOutlier_Board.service;

import java.util.List;

import com.example.MisOutlier_Board.domain.BoardDTO;

public interface BoardService {
	
	//게시물 등록
	public boolean registerBoard(BoardDTO params);
	
	//상세화면 확인
	public BoardDTO getBoardDetail(Long idx);
	
	//게시물 삭제
	public boolean deleteBoard(Long idx);
	
	//게시물 list 조회
	public List<BoardDTO> getBoardList();

}
