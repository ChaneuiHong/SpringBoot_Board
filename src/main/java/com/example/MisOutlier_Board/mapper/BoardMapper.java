package com.example.MisOutlier_Board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.MisOutlier_Board.domain.BoardDTO;

// DB와 통신 역학을 하는 Mapper 인터페이스를 생성
/*
기존의 스프링은 DAO(Data Access Object) 클래스에 @Repository를 선언해서
해당 클래스가 데이터베이스와 통신하는 클래스임을 나타내고는 하였습니다.
하지만, 마이바티스는 인터페이스에 @Mapper만 지정해주면
XML Mapper에서 메서드의 이름과 일치하는 SQL 문을 찾아 실행합니다.
Mapper 영역은 데이터베이스와의 통신, 즉 SQL 쿼리를 호출하는 것이 전부이며,
다른 로직은 전혀 필요하지 않습니다.
*/

@Mapper
public interface BoardMapper {
	
	// 작성
	public int insertBoard(BoardDTO params);
	
	// 조회
	public BoardDTO selectBoardDetail(Long idx);

	// 수정
	public int updateBoard(BoardDTO params);

	// 삭제
	public int deleteBoard(Long idx);

	// 여러개 조회
	public List<BoardDTO> selectBoardList();

	// 게시물 숫자 확인
	public int selectBoardTotalCount();
}
