package com.example.MisOutlier_Board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MisOutlier_Board.domain.BoardDTO;
import com.example.MisOutlier_Board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		
		int queryResult = 0;
		
		//신규 생성의 경우, idx가 존재하지 않음. 왜냐하면, DB INSERT 시에 자동 생성되는 값이기 때문에
		if (params.getIdx() == null) {
			boardMapper.insertBoard(params);
			queryResult = 1;
		}
		// 이미 idx가 존재하는 경우, 수정 로직 수행
		else {
			boardMapper.updateBoard(params);
			queryResult = 1;
		}
		
		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		
		BoardDTO result = boardMapper.selectBoardDetail(idx);
		
		return result;
	}

	@Override
	public boolean deleteBoard(Long idx) {
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		int queryResult = 0;
		
		// board에 값이 있거나, 이미 삭제되지 않은 경우에만 삭제 가능
		if (board != null && "N".equals(board.getDeleteYn())){
			queryResult = boardMapper.deleteBoard(idx);
		}
		
		return (queryResult==1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		// 비어있는 list를 리턴해야 할지도 모르는 경우, emptyList()를 사용한다.
		List<BoardDTO> boardList = Collections.emptyList();
		
		int cnt = boardMapper.selectBoardTotalCount();
		
		
		if (cnt>0) {
			//비어있는 list에 select로 조회된 데이터들을 그대로 넣을 수 있음.
			boardList = boardMapper.selectBoardList();
		}
		
		return boardList;
	}
	

}
